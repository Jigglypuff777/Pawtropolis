package pawtropolis.command;

import pawtropolis.command.implementations.Command;
import pawtropolis.command.implementations.InvalidCommand;
import pawtropolis.command.implementations.ParametrizedCommand;
import pawtropolis.command.utils.ClassLoaderUtil;

import java.util.*;

public class CommandFactory {
    private static CommandFactory instance = null;
    private static Set<Command> commandSet;
    private static final String PACKAGE_PATH_STRING = "pawtropolis.command.implementations";


    private CommandFactory() {
        this.commandSet = ClassLoaderUtil.createInstancesInPackage(PACKAGE_PATH_STRING);
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public static Command getCommandByString(String string) {
        String[] splitInput = string.trim().split("\\s+");
        String commandName = splitInput[0].toLowerCase();
        List<String> parameters = new ArrayList<>(Arrays.asList(splitInput).subList(1, splitInput.length));

         Command command = commandSet.stream()
                .filter(c -> c.getClass().getSimpleName().equalsIgnoreCase(commandName + "Command"))
                .findFirst()
                .orElse(new InvalidCommand());

         if ((ParametrizedCommand.class).isAssignableFrom(command.getClass())) {
             ((ParametrizedCommand)command).setParameters(parameters);
         }

         return command;
    }
}