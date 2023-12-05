package game;

import game.console.InputController;
import game.domain.Item;
import game.domain.Player;
import map.domain.Room;

import java.util.Optional;
import java.util.stream.Collectors;

public class GameController {
    private final Room entry;
    private final Player player;

    public GameController(Room entry, Player player) {
        this.entry = entry;
        this.player = player;
    }

    public void runGame() {
        Room currentRoom = entry;
        boolean gameEnded = false;

        while (!gameEnded) {
            String input;
            String output = null;
            System.out.println("What do you wanna do?");
            System.out.print(">");
            input = InputController.readString();

            String[] parts = input.split("\\s+");

            if (parts.length > 2) {
                return;
                // TODO messaggio di errore
            }

            switch (parts[0]) {
                case "go" -> {
                    if (parts[1].equals("north") || parts[1].equals("south") || parts[1].equals("west") || parts[1].equals("east")) {
                        go(currentRoom, parts[1]);
                    }
                }

                case "look" -> output = look(currentRoom);

                case "bag" -> output = bag(player);

                case "get" -> {
                    if (currentRoom.getCurrentRoomItems().stream()
                            .map(Item::getName)
                            .collect(Collectors.toSet()).contains(parts[1])) {
                        get(parts[1], currentRoom, player);
                    }
                }

                case "drop" -> {
                    if (player.getBag().getItemList().stream()
                            .map(Item::getName)
                            .collect(Collectors.toSet()).contains(parts[1])) {
                        drop(parts[1], currentRoom, player);
                    }
                }

                case "exit" -> gameEnded = true;

                default -> {
                }
                // TODO messaggio di errore
            }

            if (output != null) {
                System.out.println(output);
            }
        }
    }

    private String look(Room currentRoom) {
        return currentRoom.toString();
    }

    private String bag(Player player) {
        return player.getBag().toString();
    }

    private String go(Room currentRoom, String direction) {
        return currentRoom.toString();
    }

    private void get(String itemName, Room currentRoom, Player player) {
        Optional<Item> optionalItem = currentRoom.getCurrentRoomItems().stream()
                .filter(i -> i.getName().equals(itemName))
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
        currentRoom.getCurrentRoomItems().remove(item);
        player.getBag().setFilledSlots(player.getBag().getFilledSlots() + item.getRequiredSlots());
    }

    private void drop(String itemName, Room currentRoom, Player player) {
        Optional<Item> optionalItem = player.getBag().getItemList().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst();

        if (optionalItem.isEmpty()) {
            System.out.println("The selected item is not in the bag");
            return;
        }

        Item item = optionalItem.get();

        currentRoom.getCurrentRoomItems().add(item);
        player.getBag().getItemList().remove(item);
        player.getBag().setFilledSlots(player.getBag().getFilledSlots() - item.getRequiredSlots());
    }
}
