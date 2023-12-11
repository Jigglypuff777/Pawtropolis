package map.utils;

import animals.utils.AnimalFactory;
import game.utils.ItemFactory;
import map.domain.Room;

import java.util.*;
// TODO singleton
public class RoomFactory {

    private final Random random;
    private final AnimalFactory animalFactory;
    private final ItemFactory itemFactory;

    public RoomFactory() {
        random = new Random();
        animalFactory = new AnimalFactory();
        itemFactory = new ItemFactory();
    }

    public Room createRandomRoom() {
        Room newRoom = new Room("Room" + random.nextInt(100));
        newRoom.setAnimals(animalFactory.getRandomAnimalsSet(3));
        newRoom.setItems(itemFactory.getRandomItemsSet(5));
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