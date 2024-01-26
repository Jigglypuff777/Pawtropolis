package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.map.domain.Direction;
import pawtropolis.map.domain.Door;

import java.util.Optional;

@Component
public class GoCommand extends AbstractParametrizedCommand {
    @Autowired
    private GoCommand(GameController gameController) {
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
        Door door = gameController.getCurrentRoom().getDoorByDirection(direction);
        if (door == null) {
            System.out.println("The " + direction.toString().toLowerCase() + " room doesn't exist.");
            return;
        }

        if (gameController.changeRoomAttempt(door)) {
            System.out.println(gameController.getCurrentRoom());
        }
    }


}