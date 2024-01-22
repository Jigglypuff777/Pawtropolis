package pawtropolis.command.implementations;

import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class BagCommand extends AbstractCommand {
    private BagCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        System.out.println(gameController.getPlayer().getBagDescription());
    }
}
