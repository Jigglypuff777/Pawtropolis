package pawtropolis.game.command;

import pawtropolis.game.GameController;
import pawtropolis.game.domain.Item;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Direction;
import pawtropolis.map.domain.Room;

import java.util.*;

public enum EnumCommand implements Command {
    GO("go") {
        @Override
        public void execute(List<String> parameters) {
            String selectedDirectionName = String.join(" ", parameters);

            Optional<Direction> directionOptional = Direction.getDirectionByString(selectedDirectionName);
            if (directionOptional.isEmpty()) {
                System.out.println("Invalid direction");
                return;
            }

            Direction direction = directionOptional.get();
            Room destinationRoom = gameController.getCurrentRoom().getAdjacentRoomByDirection(direction);
            if (destinationRoom != null) {
                System.out.println(destinationRoom);
                gameController.setCurrentRoom(destinationRoom);
                return;
            }
            System.out.println("The " + direction.toString().toLowerCase() + " room doesn't exist.");
        }
    },
    GET("get") {
        @Override
        public void execute(List<String> parameters) {
            String selectedItemName = String.join(" ", parameters);
            Room currentRoom = gameController.getCurrentRoom();

            Optional<Item> itemOptional = currentRoom.getItemByName(selectedItemName);
            if (itemOptional.isEmpty()) {
                System.out.println("The selected item is not in the room");
                return;
            }

            Item item = itemOptional.get();
            Player player = gameController.getPlayer();
            if (player.addItem(item)) {
                currentRoom.removeItem(item);
            } else {
                System.out.println("You don't have enough available slots");
            }
        }
    },
    DROP("drop") {
        @Override
        public void execute(List<String> parameters) {
            String selectedItemName = String.join(" ", parameters);

            Player player = gameController.getPlayer();
            Optional<Item> itemOptional = player.getItemByName(selectedItemName);
            if (itemOptional.isEmpty()) {
                System.out.println("The selected item is not in the bag");
                return;
            }

            Room currentRoom = gameController.getCurrentRoom();
            Item item = itemOptional.get();
            player.removeItem(item);
            currentRoom.addItem(item);
        }
    },
    LOOK("look") {
        @Override
        public void execute(List<String> parameters) {
            if (parameters.isEmpty()) {
                System.out.println(gameController.getCurrentRoom());
            } else {
                System.out.println("Invalid command");
            }
        }
    },
    BAG("bag") {
        @Override
        public void execute(List<String> parameters) {
            if (parameters.isEmpty()) {
                System.out.println(gameController.getPlayer().getBagDescription());
            } else {
                System.out.println(INVALID_INPUT);
            }

        }
    },
    EXIT("exit") {
        @Override
        public void execute(List<String> parameters) {
            if (parameters.isEmpty()) {
                gameController.setGameEnded(true);
            } else {
                System.out.println(INVALID_INPUT);
            }

        }
    },
    INVALID("") {
        @Override
        public void execute(List<String> parameters) {
            System.out.println(INVALID_INPUT);
        }
    };

    private static final GameController gameController = GameController.getInstance();
    public static final String INVALID_INPUT = "Invalid input";
    private final String label;

    EnumCommand(String label) {
        this.label = label;
    }

    public static Command getCommandByString(String string) {
        return Arrays.stream(EnumCommand.values())
                .filter(command -> command.label.equals(string))
                .findFirst()
                .orElse(INVALID);
    }
}
