import game.GameController;
import game.domain.Player;
import map.domain.Room;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("gg");
        Room entry = Room.createRandomRoom();
        GameController gameController = new GameController(entry, player);
        gameController.runGame();

    }
}