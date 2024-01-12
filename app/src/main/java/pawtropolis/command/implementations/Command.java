package pawtropolis.command.implementations;

import pawtropolis.game.GameController;

public abstract class Command {
    protected static GameController gameController;

    protected Command() {
        this.gameController = GameController.getInstance();
    }

    public abstract void execute();
}