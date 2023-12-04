package map.domain;

import animals.domain.Animal;
import game.domain.Item;

import java.util.List;
import java.util.Map;

public class Room {
    private String name;
    private List<Item> itemList;
    private List<Animal> currentRoomAnimals;
    private Map<String, Room> adjacentRooms;

    public Room(String name, List<Item> itemList, List<Animal> currentRoomAnimals, Map<String, Room> adjacentRooms) {
        this.name = name;
        this.itemList = itemList;
        this.currentRoomAnimals = currentRoomAnimals;
        this.adjacentRooms = adjacentRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItem() {
        return itemList;
    }

    public void setItem(List<Item> item) {
        this.itemList = item;
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
}