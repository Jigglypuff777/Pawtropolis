package game.command;

import game.GameController;
import game.domain.Item;
import game.domain.Player;
import map.domain.Room;
import map.utils.Direction;

import java.util.Optional;

public enum EnumCommand implements Command {
    GO {
        @Override
        public void execute(String[] splitInput) {
            if (splitInput.length != 2) {
                System.out.println("You need to declare the direction");
                return;
            }

            Direction direction;
            try {
                direction = Direction.valueOf(splitInput[1].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid direction");
                return;
            }

            Room destinationRoom = gameController.getCurrentRoom().getAdjacentRoomByDirection(direction);
            if (destinationRoom != null) {
                System.out.println(destinationRoom);
                gameController.setCurrentRoom(destinationRoom);
                return;
            }
            System.out.println("The " + direction.toString().toLowerCase() + " room doesn't exist.");
        }
    },
    GET {
        @Override
        public void execute(String[] splitInput) {
            if (splitInput.length != 2) {
                System.out.println("You need to choose an item to get");
                return;
            }
            Room currentRoom = gameController.getCurrentRoom();

            Optional<Item> optionalItem = currentRoom.getItems().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(splitInput[1]))
                    .findFirst();

            if (optionalItem.isEmpty()) {
                System.out.println("The selected item is not in the room");
                return;
            }

            Player player = gameController.getPlayer();
            Item item = optionalItem.get();
            if ((item.getRequiredSlots() > player.getAvailableSlots())) {
                System.out.println("You don't have enough slots");
                return;
            }

            player.getItem(item);
            currentRoom.removePickedItem(item);
            int newAvailableSlots = player.getAvailableSlots() - item.getRequiredSlots();
            player.updateAvailableSlots(newAvailableSlots);
        }
    },
    DROP {
        @Override
        public void execute(String[] splitInput) {
            if (splitInput.length != 2) {
                System.out.println("You need to choose an item to drop");
                return;
            }

            Player player = gameController.getPlayer();
            Optional<Item> optionalItem = player.getCollectedItems().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(splitInput[1]))
                    .findFirst();
            if (optionalItem.isEmpty()) {
                System.out.println("The selected item is not in the bag");
                return;
            }

            Room currentRoom = gameController.getCurrentRoom();
            Item item = optionalItem.get();
            player.dropItem(item);
            currentRoom.receiveDroppedItem(item);
            int newAvailableSlots = player.getAvailableSlots() + item.getRequiredSlots();
            player.updateAvailableSlots(newAvailableSlots);
        }
    },
    LOOK {
        @Override
        public void execute(String[] splitInput) {
            if (splitInput.length > 1) {
                System.out.println("Invalid input");
                return;
            }
            System.out.println(gameController.getCurrentRoom());
        }
    },
    BAG {
        @Override
        public void execute(String[] splitInput) {
            if (splitInput.length > 1) {
                System.out.println("Invalid input");
                return;
            }
            System.out.println(gameController.getPlayer().getBag());
        }
    },
    EXIT {
        @Override
        public void execute(String[] splitInput) {
            gameController.setGameEnded(true);
        }
    };

    private static GameController gameController = GameController.getInstance();
}
