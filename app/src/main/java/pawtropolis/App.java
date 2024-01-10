package pawtropolis;

import pawtropolis.game.GameController;

public class App {
    public static void main(String[] args) {
        GameController gameController = GameController.getInstance();
        gameController.runGame();
    }
}