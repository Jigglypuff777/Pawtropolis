package pawtropolis.game;

import lombok.Getter;
import lombok.Setter;
import pawtropolis.command.CommandFactory;
import pawtropolis.command.implementations.Command;
import pawtropolis.game.console.InputController;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Room;
import pawtropolis.map.utils.RoomFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class GameController {
    private static GameController instance = null;
//    private static CommandFactory commandFactory;
    private static final int DEFAULT_MAP_RECURSION_DEPTH = 3;
    @Setter
    private Room currentRoom;
    private Player player;
    @Setter
    private boolean gameEnded;

    private GameController() {
//        commandFactory = CommandFactory.getInstance();
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void runGame() {
        String input;
        System.out.println("What's your name?");
        System.out.print(">");
        input = InputController.readString();
        player = new Player(input);
        currentRoom = RoomFactory.getInstance().generateGameMap(DEFAULT_MAP_RECURSION_DEPTH);
        gameEnded = false;
        System.out.println("Welcome " + player.getName() + ", let's play!");

        while (!isGameEnded()) {
            System.out.println("What do you wanna do?");
            System.out.print(">");
            input = InputController.readString();

            Command command = CommandFactory.getInstance().getCommandByString(input);
            command.execute();
        }
    }
}
