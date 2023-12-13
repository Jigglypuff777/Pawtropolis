package map.utils;

import animals.domain.Animal;
import animals.utils.AnimalFactory;
import game.domain.Item;
import game.utils.ItemFactory;
import map.domain.Room;

import java.util.*;

public class RoomFactory {
    private static RoomFactory instance = null;
    private static final Random random = new Random();
    private static final int DEFAULT_ROOM_ANIMALS_QUANTITY = 3;
    private static final int DEFAULT_ROOM_ITEMS_QUANTITY = 5;

    private RoomFactory() {
    }

    public static RoomFactory getInstance() {
        if (instance == null) {
            instance = new RoomFactory();
        }
        return instance;
    }

    public Room generateGameMap(int depth) {
        String name = "Room" + random.nextInt(100);
        Set<Item> items = ItemFactory.getInstance().getRandomItemsSet(DEFAULT_ROOM_ITEMS_QUANTITY);
        Set<Animal> animals = AnimalFactory.getInstance().getRandomAnimalsSet(DEFAULT_ROOM_ANIMALS_QUANTITY);
        Map<Direction, Room> adjacentRooms = new EnumMap<>(Direction.class);
        Room newRoom = new Room(name, items, animals, adjacentRooms);

        if (depth == 0) {
            return newRoom;
        }

        List<Direction> availableDirectionsList = new ArrayList<>(Arrays.asList(Direction.values()));
        int availableDirectionsNumber = random.nextInt(2, availableDirectionsList.size() + 1);

        for (int i = 0; i < availableDirectionsNumber; i++) {
            int randomDirectionIndex = random.nextInt(availableDirectionsList.size());
            Direction newDirection = availableDirectionsList.remove(randomDirectionIndex);

            Room linkedRoom = generateGameMap(depth - 1);
            newRoom.getAdjacentRooms().put(newDirection, linkedRoom);
            linkedRoom.getAdjacentRooms().put(Direction.getOppositeDirection(newDirection), newRoom);
        }

        return newRoom;
    }

}