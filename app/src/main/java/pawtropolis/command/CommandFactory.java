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

    @Autowired
    private ListableBeanFactory beanFactory;

    public Command createCommand(String inputString) {
        ParsedCommand parsedCommand = parseCommand(inputString);

        Map<String, Command> commandBeans = beanFactory.getBeansOfType(Command.class);

        Command command = commandBeans.values().stream()
                .filter(cmd -> cmd.getClass().getSimpleName().equalsIgnoreCase(parsedCommand.getCommandType() + "Command"))
                .findFirst()
                .orElseGet(() -> beanFactory.getBean("invalidCommand", Command.class));

        if (command instanceof ParametrizedCommand) {
            ((ParametrizedCommand) command).setParameters(parsedCommand.getParameters());
        }

        return command;
    }

    private ParsedCommand parseCommand(String inputString) {
        List<String> inputTokens = Arrays.asList(inputString.split("\s+"));

        String commandType = inputTokens.get(0);
        List<String> parameters = inputTokens.subList(1, inputTokens.size());

        return new ParsedCommand(commandType, parameters);
    }

}