package pawtropolis.command.implementations;

import java.util.List;

public abstract class ParametrizedCommand extends Command {
    protected List<String> parameters;

    protected ParametrizedCommand(List<String> parameters) {
        super();
        this.parameters = parameters;
    }
}
