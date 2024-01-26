package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Direction;
import pawtropolis.map.domain.Door;
import pawtropolis.map.utils.MapController;

import java.util.Optional;

@Component
public class GoCommand extends AbstractParametrizedCommand {
    private final MapController mapController;
    @Autowired
    private GoCommand(GameController gameController, MapController mapController) {
        super(gameController);
        this.mapController = mapController;
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
        Door door = mapController.getCurrentRoom().getDoorByDirection(direction);
        if (door == null) {
            System.out.println("The " + direction.toString().toLowerCase() + " room doesn't exist.");
            return;
        }

        Player player = gameController.getPlayer();
        if (mapController.openDoor(door, player)) {
            mapController.changeCurrentRoom(door);
        }
    }




}