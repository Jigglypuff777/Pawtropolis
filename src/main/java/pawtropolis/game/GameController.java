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
import pawtropolis.map.MapController;
import pawtropolis.map.domain.Door;
import pawtropolis.map.domain.Room;

@Component

public class GameController {
    @Getter(AccessLevel.NONE)
    private final CommandFactory commandFactory;
    private final MapController mapController;
    @Getter
    private Player player;
    @Getter
    @Setter
    private boolean gameEnded;

    @Autowired
    private GameController(CommandFactory commandFactory, MapController mapController) {
        this.commandFactory = commandFactory;
        this.mapController = mapController;
    }

    public boolean changeRoomAttempt(Door door) {
        Player player = getPlayer();
        return mapController.changeRoomAttempt(door, player);
    }

    public Room getCurrentRoom() {
        return mapController.getCurrentRoom();
    }

    public void runGame() {
        String input;
        System.out.println("What's your name?");
        System.out.print(">");
        input = InputController.readString();
        player = new Player(input);
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