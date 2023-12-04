package map.domain;

import animals.domain.Animal;
import game.domain.Item;

import java.util.List;

public class Room {
    private String name;
    private List<Item> item;
    private List<Animal> currentRoomAnimals;
    private List<Room> adjacentRooms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public List<Animal> getCurrentRoomAnimals() {
        return currentRoomAnimals;
    }

    public void setCurrentRoomAnimals(List<Animal> currentRoomAnimals) {
        this.currentRoomAnimals = currentRoomAnimals;
    }

    public List<Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void setAdjacentRooms(List<Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }
}