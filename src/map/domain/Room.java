package map.domain;

import animals.domain.Animal;
import game.domain.Item;
import map.utils.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class Room {
    private String name;
    private Set<Item> items;
    private Set<Animal> animals;
    private Map<Direction, Room> adjacentRooms;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public Map<Direction, Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void setAdjacentRooms(Map<Direction, Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }

    @Override
    public String toString() {
        String itemsString = getItems().stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "Items: ", "\n"));

        String animalsString = getAnimals().stream()
                .map(animal -> animal.getName().concat("(" + animal.getClass().getSimpleName() + ")"))
                .collect(Collectors.joining(", ", "NPC: ", ""));

        return "You are in " + getName() + "\n" + itemsString + animalsString;
    }
}