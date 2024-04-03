package pawtropolis.game.gamecontroller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;
import pawtropolis.animals.Eagle;
import pawtropolis.animals.Lion;
import pawtropolis.animals.Tiger;
import pawtropolis.game.commands.*;
import pawtropolis.game.model.Bag;
import pawtropolis.game.model.Item;
import pawtropolis.game.model.Player;
import pawtropolis.game.model.Room;
import java.time.LocalDate;
import java.util.*;

@Data
@Component
@RequiredArgsConstructor
public class GameController {
    private Room currentRoom;
    private Player player;
    private Bag bag;
    private final ListableBeanFactory beanFactory;
    private final List<Room> roomList;
    boolean gameEnd;

    public void populateGame() {
        player = new Player();
        player.setLifePoints(100);
        player.setName("Yoimiya");

        Room roomMonstadt = new Room("Monstadt");
        Room roomLiyue = new Room("Liyue");
        Room roomInazuma = new Room("Inazuma");
        Room roomSumeru = new Room("Sumeru");
        Room roomFontaine = new Room("Fontaine");

        roomList.add(roomFontaine);
        roomList.add(roomLiyue);
        roomList.add(roomMonstadt);
        roomList.add(roomSumeru);
        roomList.add(roomInazuma);

        Item item1 = new Item("long sword", "A Sword user’s Normal Attack is typically a chain of “rapid strikes”", 5);
        Item item2 = new Item("bow", "A Bow user’s Normal Attack launches a chain of fast, mid-ranged shots", 10);
        Item item3 = new Item("polearm", "A Polearm user’s Normal Attack performs a few rapid, consecutive spear strikes", 10);
        Item item4 = new Item("catalyst", "A Catalyst user applies element to enemies when they are hit with Normal Attack", 11);

        Lion lion1 = new Lion("Venti", "Ribs", 4, LocalDate.of(2019, 1, 23), 2.0, 1.28, 40);
        Lion lion2 = new Lion("Zhongli", "Chicken", 8, LocalDate.of(2015, 4, 10), 1.09, 1.17, 36);
        Lion lion3 = new Lion("Raiden", "Pork", 10, LocalDate.of(2013, 12, 5), 2.80, 1.20, 55);

        Tiger tiger1 = new Tiger("Nahida", "Meat", 3, LocalDate.of(2020, 8, 20), 2.50, 0.80, 39);
        Tiger tiger2 = new Tiger("Furina", "Ribs", 14, LocalDate.of(2009, 11, 30), 1.88, 1.10, 47);
        Tiger tiger3 = new Tiger("Neuvilette", "Pork", 8, LocalDate.of(2015, 3, 24), 1.50, 1.80, 34);

        Eagle eagle1 = new Eagle("Xiao", "Rabbit", 30, LocalDate.of(1993, 10, 18), 3.40, 0.69, 23);
        Eagle eagle2 = new Eagle("Dvalin", "Chicken", 30, LocalDate.of(1993, 5, 1), 2.48, 0.90, 33);
        Eagle eagle3 = new Eagle("Ayaka", "Mouse", 1, LocalDate.of(2023, 6, 28), 1.98, 0.45, 13);

        roomMonstadt.addAdjacents(DirectionEnum.WEST, roomLiyue);
        roomLiyue.addAdjacents(DirectionEnum.SOUTH, roomInazuma);
        roomLiyue.addAdjacents(DirectionEnum.WEST, roomSumeru);
        roomSumeru.addAdjacents(DirectionEnum.NORTH, roomFontaine);

        roomMonstadt.addItem(item1);
        roomLiyue.addItem(item2);
        roomInazuma.addItem(item4);
        roomFontaine.addItem(item3);

        roomMonstadt.addAnimal(lion1);
        roomMonstadt.addAnimal(eagle2);
        roomLiyue.addAnimal(lion2);
        roomLiyue.addAnimal(eagle1);
        roomInazuma.addAnimal(lion3);
        roomInazuma.addAnimal(eagle3);
        roomSumeru.addAnimal(tiger1);
        roomFontaine.addAnimal(tiger2);
        roomFontaine.addAnimal(tiger3);

        currentRoom = roomMonstadt;
    }

    public void startGame() {
        populateGame();
        String playerInput;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Pawtropolis!");
        System.out.println("What do want to do next? ");
        System.out.println("Type go to change the room");
        System.out.println("Type look to see what's inside the room ");
        System.out.println("Type bag to view  what's inside the bag");
        System.out.println("Type Exit to end your journey");

        while (!gameEnd) {
            System.out.print(" -> ");
            playerInput = scanner.nextLine().toLowerCase().trim();
            String[] inputParts = playerInput.split(" ", 2);
            String commandString = (inputParts[0]);
            String beanName = commandString + "Command";
            Command command;
            if(beanFactory.containsBean(beanName)){
                command = beanFactory.getBean(beanName, Command.class);
            }else{
                command = beanFactory.getBean("invalidCommand", Command.class);
            }
            command.execute(inputParts);
        }
    }
}
