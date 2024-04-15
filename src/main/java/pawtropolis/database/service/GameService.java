package pawtropolis.database.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pawtropolis.database.entity.*;
import pawtropolis.database.repo.*;
import pawtropolis.database.utils.Converter;
import pawtropolis.game.gamecontroller.DirectionEnum;
import pawtropolis.game.gamecontroller.GameController;
import pawtropolis.game.model.Bag;
import pawtropolis.game.model.Player;
import pawtropolis.game.model.Room;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameService {
    private final AdjacentRoomRepository adjacentRoomRepository;
    private final AnimalRepository animalRepository;
    private final BagRepository bagRepository;
    private final DirectionRepository directionRepository;
    private final GameRepository gameRepository;
    private final ItemRepository itemRepository;
    private final PlayerRepository playerRepository;
    private final RoomRepository roomRepository;
    private final SpeciesRepository speciesRepository;
    private final Converter converter;


    public void save(GameController gameController) {
        Player playerToSave = gameController.getPlayer();
        Bag bag = playerToSave.getBag();
        BagEntity savedBag = saveBag(bag);
        PlayerEntity savedPlayer = savePlayer(playerToSave, savedBag);

        saveItems(bag, savedBag);
        saveRooms(gameController.getRoomList());
        saveAnimals(gameController.getRoomList());
        saveItems(gameController.getRoomList());

        saveGame(savedPlayer, gameController.getCurrentRoom());
    }

    private BagEntity saveBag(Bag bag) {
        BagEntity bagEntity = converter.fromBagToEntity(bag);
        if (!isFirstGame()) {
            Optional<BagEntity> existingBagOptional = bagRepository.findById(bagEntity.getId());
            if (existingBagOptional.isPresent()) {
                BagEntity existingBag = existingBagOptional.get();
                existingBag.setOccupiedSlots(bagEntity.getOccupiedSlots());
                return bagRepository.save(existingBag);
            }
        }
        return bagRepository.save(bagEntity);
    }


    private PlayerEntity savePlayer(Player playerToSave, BagEntity savedBag) {
        return playerRepository.save(converter.fromPlayerToEntity(playerToSave, savedBag));
    }

    private void saveItems(Bag bag, BagEntity savedBag) {
        List<ItemEntity> itemsListToSave = bag.getItems().stream()
                .map(item -> itemRepository.save(converter.fromItemToEntity(item, savedBag)))
                .toList();
    }

    private void saveRooms(List<Room> roomList) {
        List<RoomEntity> roomEntitiesToSave = getRoomsToSave(roomList);
        roomRepository.saveAll(roomEntitiesToSave);
    }

    private List<RoomEntity> getRoomsToSave(List<Room> roomList) {
        List<Long> existingRoomIds = roomRepository.findAll().stream()
                .map(RoomEntity::getId)
                .toList();

        return roomList.stream()
                .filter(room -> !existingRoomIds.contains(room.getId()))
                .map(converter::fromRoomToEntity)
                .toList();
    }

    private void saveItems(List<Room> roomList) {
        roomList.forEach(room -> {
            RoomEntity roomEntity = roomRepository.findByName(room.getName());
            List<ItemEntity> itemListToSaveRoom = room.getItems().stream()
                    .map(item -> itemRepository.save(converter.fromItemToEntityRoom(item, roomEntity)))
                    .toList();
        });
    }

    private void saveAnimals(List<Room> roomList) {
        roomList.forEach(room -> {
            RoomEntity roomEntity = roomRepository.findByName(room.getName());
            List<AnimalEntity> animalListToSave = room.getAnimals().stream()
                    .map(animal -> animalRepository.save(converter.fromAnimalToEntity(animal, roomEntity)))
                    .toList();
        });
    }

    public List<Room> saveRoomList() {
        List<RoomEntity> roomEntities = roomRepository.findAll();

        Map<Long, Room> roomMap = new HashMap<>();
        for (RoomEntity roomEntity : roomEntities) {
            Room room = converter.fromEntityToRoom(roomEntity);
            room.setAnimals(converter.fromEntityListToAnimalList(animalRepository.findByRoom_Id(room.getId())));
            room.setItems(converter.fromEntityToItemList(itemRepository.findByRoom_Id(room.getId())));
            roomMap.put(room.getId(), room);
        }

        for (RoomEntity roomEntity : roomEntities) {
            Room room = roomMap.get(roomEntity.getId());
            List<AdjacentRoomEntity> adjacentRoomEntities = adjacentRoomRepository.findByRoom_Id(room.getId());
            for (AdjacentRoomEntity adjacentRoomEntity : adjacentRoomEntities) {
                DirectionEnum direction = converter.fromEntityToEnum(adjacentRoomEntity.getDirection());
                Room adjacentRoom = roomMap.get(adjacentRoomEntity.getAdjacentRoom().getId());
                room.addAdjacents(direction, adjacentRoom);
            }
        }

        return new ArrayList<>(roomMap.values());
    }

    private void saveGame(PlayerEntity savedPlayer, Room currentRoom) {
        RoomEntity roomEntity = roomRepository.findByName(currentRoom.getName());
        Optional<GameEntity> existingGameOptional = gameRepository.findByPlayer(savedPlayer);
        if (existingGameOptional.isPresent()) {
            GameEntity existingGame = existingGameOptional.get();
            existingGame.setRoom(roomEntity);
            gameRepository.save(existingGame);
        } else {
            GameEntity gameEntity = new GameEntity();
            gameEntity.setPlayer(savedPlayer);
            gameEntity.setRoom(roomEntity);
            gameRepository.save(gameEntity);
        }
    }


    public boolean isFirstGame() {
        List<PlayerEntity> playerRecords = playerRepository

                .findAll()
                .stream()
                .toList();

        if (!playerRecords.isEmpty()) {
            loadSavedRoom();
            return false;
        }
        return true;
    }


    public long loadSavedRoom() {
        List<PlayerEntity> playerRecords = playerRepository.findAll();

        long savedPlayerId = playerRecords.get(0).getId();

        GameEntity savedGame = gameRepository.findByPlayer_Id(savedPlayerId);

        return savedGame.getRoom().getId();
    }


    public Player loadSavedPlayer() {
        PlayerEntity playerEntity = playerRepository
                .findAll()
                .getFirst();
        return converter.fromEntityToPlayer(playerEntity);
    }

    public Bag loadSavedBag() {
        BagEntity bagEntity = playerRepository
                .findAll()
                .getFirst()
                .getBag();

        List<ItemEntity> itemEntities = itemRepository.findByBag_Id(bagEntity.getId());
        return converter.fromEntityToBag(bagEntity, itemEntities);
    }


}


