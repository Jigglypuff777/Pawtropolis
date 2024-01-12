package pawtropolis.command.implementations;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pawtropolis.game.GameController;

import java.util.List;

public abstract class AbstractParametrizedCommand implements ParametrizedCommand {
    protected List<String> parameters;
    protected GameController gameController;
    @Autowired
    protected AbstractParametrizedCommand(GameController gameController) {
        this.gameController = gameController;
    }
    @Override
    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}