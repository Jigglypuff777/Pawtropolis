package pawtropolis.command.implementations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pawtropolis.game.GameController;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractParametrizedCommand implements ParametrizedCommand {
    protected List<String> parameters;
    protected final GameController gameController;
    @Override
    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}