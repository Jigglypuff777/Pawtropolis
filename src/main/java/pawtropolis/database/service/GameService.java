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
import java.util.function.Function;
import java.util.stream.Collectors;

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

        saveGame(savedPlayer, gameController.getCurrentRoom());
    }

    private BagEntity saveBag(Bag bag) {
        return bagRepository.save(converter.fromBagToEntity(bag));
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
        List<String> existingRoomNames = roomRepository.findAll().stream()
                .map(RoomEntity::getName)
                .toList();

        return roomList.stream()
                .filter(room -> !existingRoomNames.contains(room.getName()))
                .map(converter::fromRoomToEntity)
                .toList();
    }

    private void saveAnimals(List<Room> roomList) { //??????????
        roomList.forEach(room -> {
            RoomEntity roomEntity = roomRepository.findByName(room.getName());
            List<ItemEntity> itemListToSaveRoom = room.getItems().stream()
                    .map(item -> itemRepository.save(converter.fromItemToEntityRoom(item, roomEntity)))
                    .toList();
            List<AnimalEntity> animalListToSave = room.getAnimals().stream()
                    .map(animal -> animalRepository.save(converter.fromAnimalToEntity(animal, roomEntity)))
                    .toList();
        });
    }

    public List<Room> saveRoomList() {
        List<RoomEntity> roomEntities = roomRepository.findAll();

        return roomEntities.stream()
                .map(roomEntity -> {
                    List<ItemEntity> itemEntitiesForRoom = itemRepository.findByRoom_Id(roomEntity.getId());
                    List<AnimalEntity> animalEntitiesForRoom = animalRepository.findByRoom_Id(roomEntity.getId());
                    List<AdjacentRoomEntity> adjacentRoomEntities = adjacentRoomRepository.findAdjacentsRooms(roomEntity.getId());

                    Map<Long, Room> roomMap = roomEntities.stream()
                            .collect(Collectors.toMap(RoomEntity::getId, room -> converter.fromEntityToRoom(room, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>())));

                    EnumMap<DirectionEnum, Room> adjacentRooms = converter.getAdjacentRoomsMap(adjacentRoomEntities, roomMap);

                    return converter.fromEntityToRoom(roomEntity, itemEntitiesForRoom, animalEntitiesForRoom, adjacentRoomEntities, roomMap);
                })
                .toList();

    }

    private void saveGame(PlayerEntity savedPlayer, Room currentRoom) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setPlayer(savedPlayer);

        RoomEntity roomEntity = roomRepository.findByName(currentRoom.getName());
        gameEntity.setRoom(roomEntity);

        gameRepository.save(gameEntity);
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


    public Room loadSavedRoom() {
        // Fetch player records
        List<PlayerEntity> playerRecords = playerRepository.findAll();

        // Retrieve saved player's ID
        long savedPlayerId = playerRecords.get(0).getId();

        // Retrieve the saved game for the player
        GameEntity savedGame = gameRepository.findByPlayer_Id(savedPlayerId);

        // Retrieve items, animals, and adjacent rooms for the saved game's room
        long savedRoomId = savedGame.getRoom().getId();
        List<ItemEntity> itemEntities = itemRepository.findByRoom_Id(savedRoomId);
        List<AnimalEntity> animalEntities = animalRepository.findByRoom_Id(savedRoomId);
        List<AdjacentRoomEntity> adjacentRoomEntities = adjacentRoomRepository.findAdjacentsRooms(savedRoomId);

        // Get the room map from saveRoomList method
        List<Room> rooms = saveRoomList();
        Map<Long, Room> roomMap = rooms.stream().collect(Collectors.toMap(Room::getId, Function.identity()));

        // Construct the enum map for adjacent rooms
        EnumMap<DirectionEnum, Room> adjacentRooms = converter.getAdjacentRoomsMap(adjacentRoomEntities, roomMap);

        // Construct and return the room
        return converter.fromEntityToRoom(savedGame.getRoom(), itemEntities, animalEntities, adjacentRoomEntities, roomMap);
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


