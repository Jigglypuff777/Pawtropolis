package projectzoo.game;

import projectzoo.domain.Room;

public class Player {

    private String namePlayer;
    private int lifePoints;
    private Bag bag;
    private Room currentRoom; // Add this field

    public Player(String name, int lifePoints, Room startingRoom, Bag bag) {
        this.namePlayer = name;
        this.lifePoints = lifePoints;
        this.bag = new Bag(30);
        this.currentRoom = startingRoom; // Set the starting room
    }

    public void viewBag() {
        for (Item item : bag.getItemList()) {
            System.out.println("- " + item.getNameItem()+" description: " + item.getDescription());
        }
    }


    public String getName() {
        return namePlayer;
    }

    public void setName(String name) {
        this.namePlayer = name;
    }

    public double getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public Bag getBag() {
        return bag;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void pickItem(String itemName) {
        Item item = currentRoom.findItem(itemName);

        if (item != null) {
            if (bag.bagUsedSlots() + item.getSlotRequired() <= bag.getAvailableSlot()) {
                bag.addItem(item);
                currentRoom.dropItem(item);
                System.out.println(itemName + " picked up.");
            } else {
                System.out.println("Not enough space in the bag.");
            }
        } else {
            System.out.println("The item is not present in this room.");
        }
    }

}