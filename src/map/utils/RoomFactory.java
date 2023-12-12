package map.utils;

import animals.domain.Animal;
import animals.utils.AnimalFactory;
import game.domain.Item;
import game.utils.ItemFactory;
import map.domain.Room;

import java.util.*;

public class RoomFactory {
    private static RoomFactory instance = null;
    private final Random random;

    private RoomFactory() {
        random = new Random();
    }

    public static RoomFactory getInstance() {
        if (instance == null) {
            instance = new RoomFactory();
        }
        return instance;
    }

    public Room createRoomTree(int depth) {
        Set<Animal> animals = AnimalFactory.getInstance().getRandomAnimalsSet(3);
        Set<Item> items = ItemFactory.getInstance().getRandomItemsSet(5);
        Map<Direction, Room> adjacentRooms = new EnumMap<>(Direction.class);
        Room newRoom = new Room("Room" + random.nextInt(100), items, animals, adjacentRooms);

        List<Direction> availableDirectionsList = new ArrayList<>(Arrays.asList(Direction.values()));
        int availableDirectionsNumber = random.nextInt(1, availableDirectionsList.size() + 1);
        for (int i = 0; i < availableDirectionsNumber; i++) {
            int randomDirectionIndex = random.nextInt(availableDirectionsList.size());
            Direction newDirection = availableDirectionsList.remove(randomDirectionIndex);
            newRoom.getAdjacentRooms().putIfAbsent(newDirection, helperCreateRoomTree(newRoom, newDirection, depth-1));
        }
        return newRoom;
    }

    public Room helperCreateRoomTree(Room linkedRoom, Direction linkedDirection, int depth) {
        Set<Animal> animals = AnimalFactory.getInstance().getRandomAnimalsSet(3);
        Set<Item> items = ItemFactory.getInstance().getRandomItemsSet(5);
        Map<Direction, Room> adjacentRooms = new EnumMap<>(Direction.class);
        adjacentRooms.put(Direction.getOppositeDirection(linkedDirection), linkedRoom);
        Room newRoom = new Room("Room" + random.nextInt(100), items, animals, adjacentRooms);
        if (depth == 0) {
            return newRoom;
        }
        List<Direction> availableDirectionsList = new ArrayList<>(Arrays.asList(Direction.values()));
        int availableDirectionsNumber = random.nextInt(1, availableDirectionsList.size() + 1);
        for (int i = 0; i < availableDirectionsNumber; i++) {
            int randomDirectionIndex = random.nextInt(availableDirectionsList.size());
            Direction newDirection = availableDirectionsList.remove(randomDirectionIndex);
            newRoom.getAdjacentRooms().putIfAbsent(newDirection, helperCreateRoomTree(newRoom, newDirection, depth-1));
        }
        return newRoom;
    }
}