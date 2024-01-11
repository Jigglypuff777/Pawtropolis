package pawtropolis.command.implementations;

import java.util.List;

public class LookCommand extends Command {
    public LookCommand() {
        super();
    }

    @Override
    public void execute() {
        System.out.println(gameController.getCurrentRoom());
    }
}
