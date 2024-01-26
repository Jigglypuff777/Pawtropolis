package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.map.utils.MapController;

@Component
public class LookCommand extends AbstractCommand {
    private final MapController mapController;
    @Autowired
    private LookCommand(GameController gameController, MapController mapController) {
        super(gameController);
        this.mapController = mapController;
    }

    @Override
    public void execute() {
        System.out.println(mapController.getCurrentRoom());
    }
}
