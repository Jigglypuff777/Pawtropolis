package map.domain;

import animals.domain.Animal;
import game.domain.Item;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {
    private String name;
    private List<Item> currentRoomItems;
    private List<Animal> currentRoomAnimals;
    private Map<String, Room> adjacentRooms;

    public Room(String name, List<Item> currentRoomItems, List<Animal> currentRoomAnimals, Map<String, Room> adjacentRooms) {
        this.name = name;
        this.currentRoomItems = currentRoomItems;
        this.currentRoomAnimals = currentRoomAnimals;
        this.adjacentRooms = adjacentRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getCurrentRoomItems() {
        return currentRoomItems;
    }

    public void setItems(List<Item> items) {
        this.currentRoomItems = items;
    }

    public List<Animal> getCurrentRoomAnimals() {
        return currentRoomAnimals;
    }

    public void setCurrentRoomAnimals(List<Animal> currentRoomAnimals) {
        this.currentRoomAnimals = currentRoomAnimals;
    }

    public Map<String, Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void setAdjacentRooms(Map<String, Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }

    @Override
    public String toString() {
        String currentRoomItemsString = this.getCurrentRoomItems().stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "Items: ", ".")) + "\n";

        String currentRoomAnimalsString = this.getCurrentRoomAnimals().stream()
                .map(animal -> animal.getName().concat("(" + animal.getClass().getSimpleName() + ")"))
                .collect(Collectors.joining(", ", "NPC: ", "."));

        return "You are in " + this.getName() + "\n" + currentRoomItemsString + currentRoomAnimalsString;
    }
}