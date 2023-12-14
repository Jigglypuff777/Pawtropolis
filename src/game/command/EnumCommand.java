package game.command;

import game.GameController;
import game.domain.Item;
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

            Optional<Item> optionalItem = gameController.getCurrentRoom().getItems().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(splitInput[1]))
                    .findFirst();

            if (optionalItem.isEmpty()) {
                System.out.println("The selected item is not in the room");
                return;
            }

            Item item = optionalItem.get();
            if ((item.getRequiredSlots() > gameController.getPlayer().getAvailableSlots())) {
                System.out.println("You don't have enough slots");
                return;
            }

            gameController.getPlayer().getItem(item);
            gameController.getCurrentRoom().removePickedItem(item);
            int newAvailableSlots = gameController.getPlayer().getAvailableSlots() - item.getRequiredSlots();
            gameController.getPlayer().updateAvailableSlots(newAvailableSlots);
        }
    },
    DROP {
        @Override
        public void execute(String[] splitInput) {
            if (splitInput.length != 2) {
                System.out.println("You need to choose an item to drop");
                return;
            }

            Optional<Item> optionalItem = gameController.getPlayer().getCollectedItems().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(splitInput[1]))
                    .findFirst();
            if (optionalItem.isEmpty()) {
                System.out.println("The selected item is not in the bag");
                return;
            }

            Item item = optionalItem.get();
            gameController.getPlayer().dropItem(item);
            gameController.getCurrentRoom().receiveDroppedItem(item);
            int newAvailableSlots = gameController.getPlayer().getAvailableSlots() + item.getRequiredSlots();
            gameController.getPlayer().updateAvailableSlots(newAvailableSlots);
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
