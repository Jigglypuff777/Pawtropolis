package map.utils;

import animals.utils.AnimalFactory;
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

//    public Room createGameMap(int depth) {
//
//    }

    public Room createRandomRoom() {
        Room newRoom = new Room("Room" + random.nextInt(100));
        newRoom.setAnimals(AnimalFactory.getInstance().getRandomAnimalsSet(3));
        newRoom.setItems(ItemFactory.getInstance().getRandomItemsSet(5));
        Map<Direction, Room> adjacentRooms = new EnumMap<>(Direction.class);

        List<Direction> availableDirectionsList = new ArrayList<>(Arrays.asList(Direction.values()));
        int availableDirectionsNumber = random.nextInt(1, availableDirectionsList.size() + 1);
        for (int i = 0; i < availableDirectionsNumber; i++) {
            int randomDirectionIndex = random.nextInt(availableDirectionsList.size());
            Direction newDirection = availableDirectionsList.remove(randomDirectionIndex);
            adjacentRooms.putIfAbsent(newDirection, null);
        }
        newRoom.setAdjacentRooms(adjacentRooms);
        return newRoom;
    }

    public Room createRandomRoom(Room linkedRoom, Direction linkedDirection) {
        Room newCurrentRoom = createRandomRoom();
        newCurrentRoom.getAdjacentRooms().put(Direction.getOppositeDirection(linkedDirection), linkedRoom);
        return newCurrentRoom;
    }
}