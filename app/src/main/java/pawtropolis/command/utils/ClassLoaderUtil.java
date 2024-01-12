package pawtropolis.command.utils;

import pawtropolis.command.implementations.Command;
import pawtropolis.command.implementations.ParametrizedCommand;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassLoaderUtil {
    private ClassLoaderUtil() {
    }

    public static Set<Command> createInstancesInPackage(String packageName) {
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
                    }
                })
                .filter(instance -> instance != null)
                .collect(Collectors.toSet());
    }

    private static Set<Class> findAllClassesUsingClassLoader(String packageName) {
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
            return null;
        }
    }
}
