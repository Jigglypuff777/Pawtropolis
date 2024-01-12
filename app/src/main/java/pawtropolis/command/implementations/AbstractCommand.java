package pawtropolis.command.implementations;

import pawtropolis.game.GameController;

public abstract class Command {
    protected static GameController gameController;

    protected Command(GameController gameController) {
        this.gameController = gameController;
    }

    public abstract void execute();
}