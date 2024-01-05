package pawtropolis.game;

import pawtropolis.animals.Eagle;
import pawtropolis.animals.Lion;
import pawtropolis.animals.Tiger;
<<<<<<< Updated upstream:src/main/java/pawtropolis/game/PopulateGame.java
import pawtropolis.game.gamecontroller.DirectionEnum;
=======
import pawtropolis.game.model.Item;
import pawtropolis.game.model.Player;
import pawtropolis.game.model.Room;
>>>>>>> Stashed changes:src/main/java/pawtropolis/game/gamecontroller/GameFactory.java


import java.time.LocalDate;

<<<<<<< Updated upstream:src/main/java/pawtropolis/game/PopulateGame.java
public class PopulateGame {
=======
public class GameFactory { //portarla di nuovo in videogamecontroller?
>>>>>>> Stashed changes:src/main/java/pawtropolis/game/gamecontroller/GameFactory.java
    private final Player player;
    private  Room currentRoom;

    public Room roomMonstadt = new Room("Monstadt");
    public Room roomLiyue = new Room("Liyue");
    public Room roomInazuma = new Room("Inazuma");
    public Room roomSumeru = new Room("Sumeru");
    public Room roomFontaine = new Room("Fontaine");

    public PopulateGame(Player player) {
        this.player = player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }


    public void gamePopulation() {

        Item item1 = new Item("long sword", "A Sword user’s Normal Attack is typically a chain of “rapid strikes”", 11);
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
        roomInazuma.addItem(item3);
        roomFontaine.addItem(item3);
        player.addItem(item4);

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
}
