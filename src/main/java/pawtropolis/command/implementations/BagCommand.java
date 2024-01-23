package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;

@Component
public class BagCommand extends AbstractCommand {
    @Autowired
    private BagCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        System.out.println(gameController.getPlayer().getBagDescription());
    }
}
