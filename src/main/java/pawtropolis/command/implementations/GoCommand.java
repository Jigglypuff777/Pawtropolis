package pawtropolis.command.implementations;

import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.map.domain.Direction;
import pawtropolis.map.domain.Room;

import java.util.List;
import java.util.Optional;

@Component
public class GoCommand extends AbstractParametrizedCommand {
    public GoCommand(GameController gameController) {
        super(gameController);
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