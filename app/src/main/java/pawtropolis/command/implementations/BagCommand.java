package pawtropolis.command.implementations;

public class BagCommand extends Command {
    @Override
    public void execute() {
        System.out.println(gameController.getPlayer().getBagDescription());
    }
}
