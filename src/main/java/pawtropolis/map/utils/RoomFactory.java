package pawtropolis.map.utils;

import pawtropolis.animals.domain.Animal;
import pawtropolis.animals.utils.AnimalFactory;
import pawtropolis.game.domain.Item;
import pawtropolis.game.utils.ItemFactory;
import pawtropolis.map.domain.Direction;
import pawtropolis.map.domain.Room;

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

        List<Direction> availableDirections = new ArrayList<>(Arrays.asList(Direction.values()));
        int randomAdjacentRoomNumber = random.nextInt(2, availableDirections.size() + 1);

        for (int i = 0; i < randomAdjacentRoomNumber; i++) {
            int randomIndex = random.nextInt(availableDirections.size());
            Direction newDirection = availableDirections.remove(randomIndex);

            Room linkedRoom = generateGameMap(depth - 1);
            newRoom.putAdjacentRoom(newDirection, linkedRoom);
            linkedRoom.putAdjacentRoom(Direction.getOppositeDirection(newDirection), newRoom);
        }

        return newRoom;
    }
}