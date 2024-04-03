package pawtropolis.database.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pawtropolis.database.entity.*;
import pawtropolis.database.repo.*;
import pawtropolis.database.utils.Converter;
import pawtropolis.game.gamecontroller.GameController;
import pawtropolis.game.model.Bag;
import pawtropolis.game.model.Item;
import pawtropolis.game.model.Player;
import pawtropolis.game.model.Room;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        BagEntity savedBag = bagRepository.save(converter.fromBagToEntity(bag));
        Room currentRoom = gameController.getCurrentRoom();

        PlayerEntity savedPlayer = playerRepository.save(converter.fromPlayerToEntity(playerToSave, savedBag));

        List<ItemEntity> itemsListToSave = bag.getItems().stream()
                .map(item -> itemRepository.save(converter.fromItemToEntity(item, savedBag))).collect(Collectors.toList());

        List<Room> roomListToSave = gameController.getRoomList();
        saveRoom(roomListToSave);

        saveItems(itemsListToSave);
        saveGame(savedPlayer, currentRoom);

    }

    public void saveItems(List<ItemEntity> itemEntitiesToSave, Room currentRoom) {
        // Save items
        itemRepository.saveAll(itemEntitiesToSave);

        // Save the room where the items were taken
        RoomEntity roomEntity = roomRepository.findByName(currentRoom.getName());
        for (ItemEntity itemEntity : itemEntitiesToSave) {
            roomEntity.addItem(itemEntity);
        }
        roomRepository.save(roomEntity);
    }

    public void saveRoom(List<Room> roomList) {
        List<String> existingRoomNames = roomRepository.findAll().stream()
                .map(RoomEntity::getName)
                .toList();

        List<Room> roomToSave = roomList.stream()
                .filter(room -> !existingRoomNames.contains(room.getName()))
                .toList();

        List<RoomEntity> roomEntitiesToSave = roomToSave.stream()
                .map(converter::fromRoomToEntity)
                .toList();

        roomRepository.saveAll(roomEntitiesToSave);
    }

    public void saveGame(PlayerEntity playerEntity, Room currentRoom) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setPlayer(playerEntity);

        RoomEntity roomEntity = roomRepository.findByName(currentRoom.getName());
        gameEntity.setRoom(roomEntity);

        gameRepository.save(gameEntity);
    }
}
