package pawtropolis.command.implementations;

import lombok.Setter;
import pawtropolis.game.GameController;

import java.util.List;

public abstract class ParametrizedCommand extends Command {
    @Setter
    protected List<String> parameters;

    protected ParametrizedCommand(GameController gameController, List<String> parameters) {
        super(gameController);
        this.parameters = parameters;
    }
}
