package pawtropolis.command;

import pawtropolis.command.implementations.Command;
import pawtropolis.command.implementations.InvalidCommand;
import pawtropolis.command.implementations.ParametrizedCommand;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class CommandFactory {
    private static CommandFactory instance = null;
    private static Set<Command> commandSet = null;
    private static final String PACKAGE_PATH_STRING = "pawtropolis.command.implementations";

    private CommandFactory() {
        this.commandSet = createInstancesInPackage(PACKAGE_PATH_STRING);
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    private static Set<Command> createInstancesInPackage(String packageName) {
        return findAllClassesUsingClassLoader(packageName)
                .stream()
                .map(clazz -> {
                    if (Modifier.isAbstract(clazz.getModifiers())) {
                        return null;
                    }

                    try {
                        if ((ParametrizedCommand.class).isAssignableFrom(clazz)) {
                            return (Command) clazz.asSubclass(ParametrizedCommand.class).getConstructor(List.class).newInstance(new ArrayList<>());
                        } else {
                            return (Command) clazz.getConstructor().newInstance();
                        }
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                             InvocationTargetException e) {
                        e.printStackTrace();
                        return null;
                    }})
                .filter(instance -> instance != null)
                .collect(Collectors.toSet());
    }

    public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
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