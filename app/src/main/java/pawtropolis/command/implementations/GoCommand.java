package pawtropolis.command.implementations;

import pawtropolis.map.domain.Direction;
import pawtropolis.map.domain.Room;

import java.util.List;
import java.util.Optional;

public class GoCommand extends ParametrizedCommand {
    public GoCommand(List<String> parameters) {
        super(parameters);
    }

    @Override
    public void execute() {
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
}