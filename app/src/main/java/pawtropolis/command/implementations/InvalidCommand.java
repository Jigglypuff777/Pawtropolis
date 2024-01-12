package pawtropolis.command.implementations;

import java.util.List;

public class InvalidCommand extends Command {
    @Override
    public void execute() {
        System.out.println("Invalid input");
    }
}
