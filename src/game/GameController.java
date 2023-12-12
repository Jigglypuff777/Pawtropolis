package game;

import game.command.Command;
import game.command.EnumCommand;
import game.console.InputController;
import game.domain.Item;
import game.domain.Player;
import map.domain.Room;
import map.utils.Direction;
import map.utils.RoomFactory;

import java.util.Optional;

public class GameController {
    private static final String INVALID_INPUT_STRING = "Invalid input";
    private static final int DEFAULT_ROOM_TREE_DEPTH = 3;
    private static GameController instance = null;
    private Room currentRoom;
    private Player player;
    private boolean gameEnded;

    private GameController(){
        this.currentRoom = RoomFactory.getInstance().createRoomTree(DEFAULT_ROOM_TREE_DEPTH);
        this.player = new Player();
        this.gameEnded = false;
    }

    public static GameController getInstance(){
        if (instance == null){
            instance = new GameController();
        }
        return instance;
    }

    public void runGame() {
        String input;

        while (!gameEnded) {
            System.out.println("What do you wanna do?");
            System.out.print(">");
            input = InputController.readString();

            String[] parts = input.trim().split("\\s+");

            if (parts.length > 2) {
                System.out.println(INVALID_INPUT_STRING);
            }

            else if (parts.length > 0) {
                try {
                    EnumCommand command = EnumCommand.valueOf(parts[0].toUpperCase());
                    command.execute(parts);
                } catch (IllegalArgumentException exception) {
                    System.out.println(INVALID_INPUT_STRING);
                }
            }

            else {
                System.out.println(INVALID_INPUT_STRING);
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }
}
