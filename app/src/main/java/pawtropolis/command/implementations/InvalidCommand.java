package pawtropolis.command.implementations;

import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class InvalidCommand extends AbstractCommand {
    protected InvalidCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        System.out.println("Invalid input");
    }
}
