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
    public Room createRandomRoom() {
        Room newCurrentRoom = new Room();
        newCurrentRoom.setName("Room" + random.nextInt(100));
        newCurrentRoom.setCurrentRoomAnimals(animalFactory.getRandomAnimalsSet(3));
        newCurrentRoom.setCurrentRoomItems(itemFactory.getRandomItemsSet(5));
        Map<Direction, Room> newMap = new EnumMap<>(Direction.class);

        List<Direction> availableDirectionsList = new ArrayList<>(Arrays.asList(Direction.values()));
        int additionalDoors = random.nextInt(1, 5);
        for (int i = 0; i < additionalDoors; i++) {
            Direction newDirection = availableDirectionsList.remove(random.nextInt(availableDirectionsList.size()));
            newMap.putIfAbsent(newDirection, null);
        }
        newCurrentRoom.setAdjacentRooms(newMap);
        System.out.println(newCurrentRoom.getAdjacentRooms().keySet());
        return newCurrentRoom;
    }

    public Room createRandomRoom(Room previousRoom, Direction previousDirection) {
        Room newCurrentRoom = new Room();
        newCurrentRoom.setName("Room" + random.nextInt(100));
        newCurrentRoom.setCurrentRoomAnimals(animalFactory.getRandomAnimalsSet(3));
        newCurrentRoom.setCurrentRoomItems(itemFactory.getRandomItemsSet(5));
        Map<Direction, Room> newMap = new EnumMap<>(Direction.class);
        newMap.put(Direction.getOppositeDirection(previousDirection), previousRoom);

        List<Direction> availableDirectionsList = new ArrayList<>(Arrays.asList(Direction.values()));
        availableDirectionsList.remove(Direction.getOppositeDirection(previousDirection));
        int additionalDoors = random.nextInt(1, 4);
        for (int i = 0; i < additionalDoors; i++) {
            Direction newDirection = availableDirectionsList.remove(random.nextInt(availableDirectionsList.size()));
            newMap.putIfAbsent(newDirection, null);
        }
        newCurrentRoom.setAdjacentRooms(newMap);
        System.out.println(newCurrentRoom.getAdjacentRooms().keySet());
        return newCurrentRoom;
    }
}