package projectzoo.domain;

import projectzoo.animals.Animal;
import projectzoo.game.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.swing.SpringLayout.*;

public class Room {
    private String roomName;
    private final List<Item> availableItems;
    private List<Animal> availableAnimals;
    private final Map<String, Room> adjacents;

    public Room(String roomName) {
        this.roomName = roomName;
        this.availableItems = new ArrayList<>();
        this.availableAnimals = new ArrayList<>();
        this.adjacents = new HashMap<>();
    }

    public void addItem(Item item) {
        availableItems.add(item);
    }

    public void addAnimal(Animal animal) {
        availableAnimals.add(animal);
    }

    public void addAdjacents(String direction, Room currentRoom) {
        adjacents.put(direction, currentRoom);
    }

    public String getRoomName() {
        return roomName;
    }

    public Map<String, Room> getAdjacents() {
        return adjacents;
    }


    public void lookRoom() {
        System.out.println("You're in the room: " + roomName);
        System.out.println("Available directions: " + String.join(", ", adjacents.keySet()));

        if (!availableItems.isEmpty()) {
            System.out.println("Available items");
            for (Item item : availableItems) {
                System.out.println("- " + item.getNameItem() + ": " + item.getDescription());
            }} else {
            System.out.println("There are no items in this room");
        }
        if (!availableAnimals.isEmpty()) {
            System.out.println("NPC: ");
            for (Animal animal : availableAnimals) {
                System.out.println("- " + animal.getNickname() + "(" + animal.getClass().getSimpleName() + ")");
            }}else {
                System.out.println("There are no NPCs in this room");
            }
        }

        public void dropItem (Item item){
            availableItems.remove(item);
        }

        public Item findItem (String itemName){
            for (Item item : availableItems) {
                if (item.getNameItem().equalsIgnoreCase(itemName)) {
                    return item;
                }
            }
            return null;
        }

        public void setRoomName (String roomName){
            this.roomName = roomName;
        }

        public void getAdjacents (String direction){
            adjacents.get(direction);
        }

        public List<Item> getAvailableItems () {
            return availableItems;
        }

        public List<Animal> getAvailableAnimals () {
            return availableAnimals;
        }

        public void setAvailableAnimals (List < Animal > availableAnimals) {
            this.availableAnimals = availableAnimals;
        }



}