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
            System.out.println("What do you wanna do?");
            System.out.print(">");
            input = InputController.readString();

            String[] parts = input.split("\\s+");

            if (parts.length > 2) {
                System.out.println("Invalid input");
                continue;
            }

            switch (parts[0].toLowerCase()) {
                case "go" -> {
                    if (parts.length == 2) {
                        currentRoom = go(currentRoom, parts[1]);
                    } else {
                        System.out.println("Invalid input");
                    }
                }

                case "look" -> look(currentRoom);

                case "bag" -> bag(player);

                case "get" -> {
                    if (parts.length == 2) {
                        get(parts[1], currentRoom, player);
                    } else {
                        System.out.println("Invalid input");
                    }
                }

                case "drop" -> {
                    if (parts.length == 2) {
                        drop(parts[1], currentRoom, player);
                    } else {
                        System.out.println("Invalid input");
                    }
                }

                case "exit" -> gameEnded = true;

                default -> {
                    System.out.println("Invalid input");
                }

            }
        }
    }

    private void look(Room currentRoom) {
        System.out.println(currentRoom.toString());
    }

    private void bag(Player player) {
        System.out.println(player.getBag().toString());
    }

    private Room go(Room currentRoom, String direction) {
        // controlliamo se l'input NON è giusto, in particolare se la seconda parola splittata è effettivamente una
        // delle quattro valide (north, south...)
         if (!("north".equalsIgnoreCase(direction) || "south".equalsIgnoreCase(direction)
                 || "west".equalsIgnoreCase(direction) || "east".equalsIgnoreCase(direction))) {
             System.out.println("Invalid input");
             return currentRoom;
        }

         // se siamo qui, l'input è giusto
        direction = direction.toLowerCase();

        // ora check se la stanza ha la porta richiesta
        if (currentRoom.getAdjacentRooms().containsKey(direction)){

            //controllo se oltre la porta ci sia gia una stanza creata
            if (currentRoom.getAdjacentRooms().get(direction) == null){
                //colleghiamo le due stanze, creando la nuova e legandole con una porta comune ed inversa
                currentRoom.getAdjacentRooms().put(direction, Room.createRandomRoom(currentRoom, direction));
            }

            // prendiamo la nuova stanza
            Room newCurrentRoom = currentRoom.getAdjacentRooms().get(direction);

            //la stampiamo
            System.out.println(newCurrentRoom.toString());

            //ritorniamo la nuova room che nel runGame sarà la nuova currentRoom
            return newCurrentRoom;
        }

        // se non esiste la stanza
        System.out.println("The " + direction + " room doesn't exist.");

        return currentRoom;


    }

    private void get(String itemName, Room currentRoom, Player player) {
        Optional<Item> optionalItem = currentRoom.getCurrentRoomItems().stream()
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
