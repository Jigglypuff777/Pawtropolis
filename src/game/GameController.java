package game;

import game.console.InputController;
import game.domain.Item;
import game.domain.Player;
import game.utils.ItemFactory;
import map.domain.Room;
import map.utils.Direction;
import map.utils.RoomFactory;

import java.util.Optional;
import java.util.Random;

public class GameController {
    private static GameController instance = null;
    private static final String INVALID_INPUT_STRING = "Invalid input";
    private static final int DEFAULT_ROOM_TREE_DEPTH = 3;
    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void runGame() {
        Room currentRoom = RoomFactory.getInstance().createRoomTree(DEFAULT_ROOM_TREE_DEPTH);
        boolean gameEnded = false;
        String input;

        System.out.println("Hello, what's your name?");
        System.out.print(">");
        input = InputController.readString();
        Player player = new Player(input);
        System.out.println("Welcome, " + input);

        while (!gameEnded) {
            System.out.println("What do you wanna do?");
            System.out.print(">");
            input = InputController.readString();

            String[] parts = input.trim().split("\\s+");

            if (parts.length > 2) {
                System.out.println(INVALID_INPUT_STRING);
            }

            else if (parts.length == 2) {
                switch (parts[0].toLowerCase()) {
                    case "go" ->  currentRoom = go(currentRoom, parts[1]);
                    case "drop" -> drop(parts[1], currentRoom, player);
                    case "get" -> get(parts[1], currentRoom, player);
                    default -> System.out.println(INVALID_INPUT_STRING);
                }
            }

            else if (parts.length == 1) {
                switch (parts[0].toLowerCase()) {
                    case "look" -> look(currentRoom);
                    case "bag" -> bag(player);
                    case "exit" -> gameEnded = true;
                    default -> System.out.println(INVALID_INPUT_STRING);
                }
            }

            else {
                System.out.println(INVALID_INPUT_STRING);
            }
        }
    }

    private void look(Room currentRoom) {
        System.out.println(currentRoom.toString());
    }

    private void bag(Player player) {
        System.out.println(player.getBag().toString());
    }

    private Room go(Room currentRoom, String inputString) {
        Direction direction;

        try {
            direction = Direction.valueOf(inputString.toUpperCase());
        } catch (IllegalArgumentException exception) {
            System.out.println(INVALID_INPUT_STRING);
            return currentRoom;
        }

        // ora check se la stanza ha la porta richiesta
        if (currentRoom.getAdjacentRooms().get(direction) != null) {
            // prendiamo la nuova stanza
            Room newCurrentRoom = currentRoom.getAdjacentRooms().get(direction);

            //la stampiamo
            System.out.println(newCurrentRoom.toString());

            //ritorniamo la nuova room che nel runGame sarà la nuova currentRoom
            return newCurrentRoom;
        }

        // se non esiste la stanza
        System.out.println("The " + direction.toString().toLowerCase() + " room doesn't exist.");
        return currentRoom;
    }

    private void get(String itemName, Room currentRoom, Player player) {
        Optional<Item> optionalItem = currentRoom.getItems().stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst();

        if (optionalItem.isEmpty()) {
            System.out.println("The selected item is not in the room");
            return;
        }

        Item item = optionalItem.get();

        if ((item.getRequiredSlots() + player.getBag().getFilledSlots()) > player.getBag().getTotalSlots()) {
            System.out.println("You don't have enough slots");
            return;
        }

        player.getBag().getItemList().add(item);
        currentRoom.getItems().remove(item);
        player.getBag().setFilledSlots(player.getBag().getFilledSlots() + item.getRequiredSlots());
    }

    private void drop(String itemName, Room currentRoom, Player player) {
        Optional<Item> optionalItem = player.getBag().getItemList().stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst();

        if (optionalItem.isEmpty()) {
            System.out.println("The selected item is not in the bag");
            return;
        }

        Item item = optionalItem.get();

        currentRoom.getItems().add(item);
        player.getBag().getItemList().remove(item);
        player.getBag().setFilledSlots(player.getBag().getFilledSlots() - item.getRequiredSlots());
    }
}
