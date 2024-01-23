package pawtropolis.command;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.command.implementations.Command;
import pawtropolis.command.implementations.ParametrizedCommand;
import pawtropolis.command.utils.ParsedCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CommandFactory {

    private final ListableBeanFactory beanFactory;

    @Autowired
    private CommandFactory(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Command getCommandByString(String inputString) {
        ParsedCommand parsedCommand = parseCommand(inputString);

        Map<String, Command> commandBeans = beanFactory.getBeansOfType(Command.class);

        Command command = commandBeans.keySet().stream()
                .filter(cmd -> cmd.equalsIgnoreCase(parsedCommand.getCommandType() + "Command"))
                .map(commandBeans::get)
                .findFirst()
                .orElseGet(() -> beanFactory.getBean("invalidCommand", Command.class));

        if (command instanceof ParametrizedCommand) {
            ((ParametrizedCommand) command).setParameters(parsedCommand.getParameters());
        } else {
            if (notParametrizedCommandHasParameters(parsedCommand)) {
                command = beanFactory.getBean("invalidCommand", Command.class);
            }
        }

        return command;
    }

    private ParsedCommand parseCommand(String inputString) {
        List<String> inputTokens = Arrays.asList(inputString.split("\\s+"));

        String commandType = inputTokens.get(0);
        List<String> parameters = inputTokens.subList(1, inputTokens.size());

        return new ParsedCommand(commandType, parameters);
    }

    private boolean notParametrizedCommandHasParameters(ParsedCommand parsedCommand) {
        return !(parsedCommand.getParameters().isEmpty());
    }
}