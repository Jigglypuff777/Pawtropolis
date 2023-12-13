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
                System.out.println("Direction not valid");
                return;
            }


            if (gameController.getCurrentRoom().getAdjacentRooms().get(direction) != null) {

                Room newCurrentRoom = gameController.getCurrentRoom().getAdjacentRooms().get(direction);

                System.out.println(newCurrentRoom.toString());

                gameController.setCurrentRoom(newCurrentRoom);

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

            if ((item.getRequiredSlots() + gameController.getPlayer().getBag().getFilledSlots()) > gameController.getPlayer().getBag().getTotalSlots()) {
                System.out.println("You don't have enough slots");
                return;
            }

            gameController.getPlayer().getBag().getItemList().add(item);
            gameController.getCurrentRoom().getItems().remove(item);
            gameController.getPlayer().getBag().setFilledSlots(gameController.getPlayer().getBag().getFilledSlots() + item.getRequiredSlots());

        }
    },
    DROP {
        @Override
        public void execute(String[] splitInput) {
            if (splitInput.length != 2) {
                System.out.println("You need to choose an item to drop");
                return;
            }
            Optional<Item> optionalItem = gameController.getPlayer().getBag().getItemList().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(splitInput[1]))
                    .findFirst();

            if (optionalItem.isEmpty()) {
                System.out.println("The selected item is not in the bag");
                return;
            }

            Item item = optionalItem.get();

            gameController.getCurrentRoom().getItems().add(item);
            gameController.getPlayer().getBag().getItemList().remove(item);
            gameController.getPlayer().getBag().setFilledSlots(gameController.getPlayer().getBag().getFilledSlots() - item.getRequiredSlots());
        }
    },
    LOOK {
        @Override
        public void execute(String[] splitInput) {
            System.out.println(gameController.getCurrentRoom().toString());
        }
    },
    BAG {
        @Override
        public void execute(String[] splitInput) {
            System.out.println(gameController.getPlayer().getBag().toString());
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
