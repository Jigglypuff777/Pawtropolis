package pawtropolis.command.implementations;

import lombok.Setter;

import java.util.List;

public abstract class ParametrizedCommand extends Command {
    @Setter
    protected List<String> parameters;

    protected ParametrizedCommand(List<String> parameters) {
        super();
        this.parameters = parameters;
    }
}
