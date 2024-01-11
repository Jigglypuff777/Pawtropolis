package pawtropolis.command.implementations;

public class ExitCommand extends Command {
    @Override
    public void execute() {
            gameController.setGameEnded(true);
    }
}
