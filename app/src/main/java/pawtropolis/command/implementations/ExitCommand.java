package pawtropolis.command.implementations;

import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class ExitCommand extends AbstractCommand {
    public ExitCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
            gameController.setGameEnded(true);
    }
}
