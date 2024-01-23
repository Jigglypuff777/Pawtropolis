package pawtropolis.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.command.CommandFactory;
import pawtropolis.command.implementations.Command;
import pawtropolis.game.console.InputController;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Room;
import pawtropolis.map.utils.RoomFactory;

@Component
@Getter
public class GameController {
    private static final int DEFAULT_MAP_RECURSION_DEPTH = 3;
    @Getter(AccessLevel.NONE)
    private final CommandFactory commandFactory;
    private Player player;
    @Setter
    private Room currentRoom;
    @Setter
    private boolean gameEnded;

    @Autowired
    private GameController(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
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

            Command command = commandFactory.getCommandByString(input);
            command.execute();
        }
    }
}