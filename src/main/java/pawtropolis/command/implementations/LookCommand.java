package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class LookCommand extends AbstractCommand {
    @Autowired
    private LookCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        System.out.println(gameController.getCurrentRoom());
    }
}
