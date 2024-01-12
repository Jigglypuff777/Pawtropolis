package pawtropolis.command.implementations;

import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class LookCommand extends AbstractCommand {
    public LookCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        System.out.println(gameController.getCurrentRoom());
    }
}
