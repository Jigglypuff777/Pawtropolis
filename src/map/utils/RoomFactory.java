package map.utils;

import animals.utils.AnimalFactory;
import game.utils.ItemFactory;
import map.domain.Room;

import java.util.*;

public class RoomFactory {

    private final Random random = new Random();

    private final AnimalFactory animalFactory = new AnimalFactory();
    private final ItemFactory itemFactory = new ItemFactory();

    // TODO rimuovere??
    public RoomFactory() {
    }

    // TODO overloaded??
    public Room createRandomRoom(Room previousRoom, String previousDirection) {
        Room newCurrentRoom = new Room();
        newCurrentRoom.setName("Room" + random.nextInt(100));
        newCurrentRoom.setCurrentRoomAnimals(animalFactory.getRandomAnimalsSet(3));
        newCurrentRoom.setCurrentRoomItems(itemFactory.getRandomItemsSet(5));
        Map<Direction, Room> newMap = new EnumMap<>(Direction.class);
        newMap.put(Direction.getOppositeDirection(previousDirection), previousRoom);

        int additionalDoors = random.nextInt(1, 4);
        for (int i = 0; i < additionalDoors; i++) {
            newMap.putIfAbsent(Direction.randomDirection(), null);
        }
        newCurrentRoom.setAdjacentRooms(newMap);
        System.out.println(newCurrentRoom.getAdjacentRooms().keySet());
        return newCurrentRoom;
    }

    public Room createFirstRandomRoom() {
        Room newCurrentRoom = new Room();
        newCurrentRoom.setName("Room" + random.nextInt(100));
        newCurrentRoom.setCurrentRoomAnimals(animalFactory.getRandomAnimalsSet(3));
        newCurrentRoom.setCurrentRoomItems(itemFactory.getRandomItemsSet(5));
        Map<Direction, Room> newMap = new EnumMap<>(Direction.class);

        int additionalDoors = random.nextInt(1, 4);
        for (int i = 0; i < additionalDoors; i++) {
            newMap.putIfAbsent(Direction.randomDirection(), null);
        }
        newCurrentRoom.setAdjacentRooms(newMap);
        System.out.println(newCurrentRoom.getAdjacentRooms().keySet());
        return newCurrentRoom;
    }
}