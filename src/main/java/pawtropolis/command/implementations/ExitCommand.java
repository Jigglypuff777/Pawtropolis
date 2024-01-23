package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class ExitCommand extends AbstractCommand {
    @Autowired
    private ExitCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
            gameController.setGameEnded(true);
    }
}
