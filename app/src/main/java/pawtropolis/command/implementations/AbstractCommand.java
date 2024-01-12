package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.game.GameController;

public abstract class AbstractCommand implements Command {
    protected GameController gameController;
    @Autowired
    protected AbstractCommand(GameController gameController) {
        this.gameController = gameController;
    }
}