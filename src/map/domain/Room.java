package map.domain;

import animals.domain.Animal;
import game.domain.Item;
import map.utils.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class Room {
    private final String name;
    private final Set<Item> items;
    private final Set<Animal> animals;
    private final Map<Direction, Room> adjacentRooms;

    public Room(String name, Set<Item> items, Set<Animal> animals, Map<Direction, Room> adjacentRooms) {
        this.name = name;
        this.items = items;
        this.animals = animals;
        this.adjacentRooms = adjacentRooms;
    }

    public String getName() {
        return name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public Map<Direction, Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public Room getAdjacentRoomByDirection(Direction direction) {
        return adjacentRooms.get(direction);
    }

    public void putAdjacentRoom(Direction linkedDirection, Room linkedRoom) {
        adjacentRooms.put(linkedDirection, linkedRoom);
    }

    public void removePickedItem(Item item) {
        items.remove(item);
    }

    public void receiveDroppedItem(Item item) {
        items.add(item);
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