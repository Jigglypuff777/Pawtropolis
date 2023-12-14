package game;

import game.command.Command;
import game.command.EnumCommand;
import game.console.InputController;
import game.domain.Player;
import map.domain.Room;
import map.utils.RoomFactory;


public class GameController {
    private static GameController instance = null;
    private static final int DEFAULT_MAP_RECURSION_DEPTH = 3;
    private Room currentRoom;
    private final Player player;
    private boolean gameEnded;

    private GameController() {
        this.currentRoom = RoomFactory.getInstance().generateGameMap(DEFAULT_MAP_RECURSION_DEPTH);
        this.player = new Player();
        this.gameEnded = false;
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void runGame() {

        String input;

        while (!isGameEnded()) {
            System.out.println("What do you wanna do?");
            System.out.print(">");
            input = InputController.readString();

            String[] splitInput = input.trim().split("\\s+");

            if (splitInput.length > 2 || splitInput.length == 0) {
                System.out.println("Invalid input");
                continue;
            }

            try {
                Command command = EnumCommand.valueOf(splitInput[0].toUpperCase());
                command.execute(splitInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command");
            }

        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Player getPlayer() {
        return player;

    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }
}