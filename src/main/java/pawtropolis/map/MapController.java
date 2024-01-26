package pawtropolis.map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pawtropolis.animals.domain.Animal;
import pawtropolis.animals.utils.AnimalFactory;
import pawtropolis.game.console.InputController;
import pawtropolis.game.domain.Item;
import pawtropolis.game.domain.Player;
import pawtropolis.game.utils.ItemFactory;
import pawtropolis.map.domain.Direction;
import pawtropolis.map.domain.Door;
import pawtropolis.map.domain.Room;

import java.util.*;

@Getter
@Setter
@Component
public class MapController {
    private static final Random random = new Random();
    private static final int DEFAULT_ROOM_ANIMALS_QUANTITY = 3;
    private static final int DEFAULT_ROOM_ITEMS_QUANTITY = 5;
    private static final int DEFAULT_MAP_RECURSION_DEPTH = 3;
    private Room currentRoom;

    private MapController() {
        currentRoom = generateGameMap(DEFAULT_MAP_RECURSION_DEPTH);
    }

    public Room generateGameMap(int depth) {
        String name = "Room" + random.nextInt(100);
        Set<Item> items = ItemFactory.getInstance().getRandomItemsSet(DEFAULT_ROOM_ITEMS_QUANTITY);
        Set<Animal> animals = AnimalFactory.getInstance().getRandomAnimalsSet(DEFAULT_ROOM_ANIMALS_QUANTITY);
        Map<Direction, Door> doors = new EnumMap<>(Direction.class);
        Room newRoom = new Room(name, items, animals, doors);

        if (depth == 0) {
            return newRoom;
        }

        List<Direction> availableDirections = new ArrayList<>(Arrays.asList(Direction.values()));
        int randomAdjacentRoomNumber = random.nextInt(2, availableDirections.size() + 1);

        for (int i = 0; i < randomAdjacentRoomNumber; i++) {
            int randomIndex = random.nextInt(availableDirections.size());
            Direction newDirection = availableDirections.remove(randomIndex);

            Room linkedRoom = generateGameMap(depth - 1);
            Door door = new Door(newRoom, linkedRoom);
            newRoom.putDoor(newDirection, door);
            linkedRoom.putDoor(Direction.getOppositeDirection(newDirection), door);
        }
        return newRoom;
    }


    public boolean changeRoomAttempt(Door door, Player player) {
        if (!door.isLocked()) {
            changeCurrentRoom(door);
            return true;
        }

        if (askUserIfUnlock() && handleUnlockingAttempt(door, player)) {
            door.setLocked(false);
            changeCurrentRoom(door);
            return true;
        }
        return false;
    }

    private boolean askUserIfUnlock() {
        System.out.println("The door is locked: would you like to use an item to unlock it? (y/n)");
        String input = InputController.readString();
        if ("Y".equalsIgnoreCase(input)) {
            return true;
        } else if ("N".equalsIgnoreCase(input)) {
            return false;
        } else {
            System.out.println("Invalid input");
            return false;
        }
    }

    private boolean handleUnlockingAttempt(Door door, Player player) {
        System.out.println("Type the name of the chosen item");
        String input = InputController.readString();
        Optional<Item> itemOptional = player.getItemByName(input);
        if (itemOptional.isEmpty()) {
            System.out.println("The selected item is not in the bag");
            return false;
        }
        Item item = itemOptional.get();
        if (!(door.getUnlockingItem().equals(item))) {
            System.out.println("This is not the right item");
            return false;
        }
        player.removeItem(item);
        return true;
    }

    private void changeCurrentRoom(Door door) {
        currentRoom = door.getDestinationRoom();
        door.swapRooms();
    }
}