package pawtropolis.game;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.command.CommandFactory;
import pawtropolis.command.implementations.AbstractCommand;
import pawtropolis.command.implementations.Command;
import pawtropolis.game.console.InputController;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Room;
import pawtropolis.map.utils.RoomFactory;

@Component
@Getter
public class GameController {
    private static final int DEFAULT_MAP_RECURSION_DEPTH = 3;
    @Setter
    private Room currentRoom;
    private Player player;
    @Setter
    private boolean gameEnded;
    @Autowired
    private CommandFactory commandFactory;

    public void runGame() {;
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

            Command command = commandFactory.createCommand(input);
            command.execute();
        }
    }
}