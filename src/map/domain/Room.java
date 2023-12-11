package map.domain;

import animals.domain.Animal;
import game.domain.Item;
import map.utils.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class Room {
    private String name;
    private Set<Item> currentRoomItems;
    private Set<Animal> currentRoomAnimals;
    private Map<Direction, Room> adjacentRooms;

    // TODO rimuovere???
    public Room() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getCurrentRoomItems() {
        return currentRoomItems;
    }

    public void setCurrentRoomItems(Set<Item> items) {
        this.currentRoomItems = items;
    }

    public Set<Animal> getCurrentRoomAnimals() {
        return currentRoomAnimals;
    }

    public void setCurrentRoomAnimals(Set<Animal> currentRoomAnimals) {
        this.currentRoomAnimals = currentRoomAnimals;
    }

    public Map<Direction, Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void setAdjacentRooms(Map<Direction, Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }

    @Override
    public String toString() {
        String currentRoomItemsString = this.getCurrentRoomItems().stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "Items: ", "\n"));

        String currentRoomAnimalsString = this.getCurrentRoomAnimals().stream()
                .map(animal -> animal.getName().concat("(" + animal.getClass().getSimpleName() + ")"))
                .collect(Collectors.joining(", ", "NPC: ", ""));

        return "You are in " + this.getName() + "\n" + currentRoomItemsString + currentRoomAnimalsString;
    }
}