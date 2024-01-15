package pawtropolis.command.implementations;

import java.util.List;

public interface ParametrizedCommand extends Command {
    void setParameters(List<String> parameters);
}
