package pawtropolis.command.utils;

import java.util.List;

public class ParsedCommand {
    private final String commandType;
    private final List<String> parameters;

    public ParsedCommand(String commandType, List<String> parameters) {
        this.commandType = commandType;
        this.parameters = parameters;
    }

    public String getCommandType() {
        return commandType;
    }

    public List<String> getParameters() {
        return parameters;
    }
}