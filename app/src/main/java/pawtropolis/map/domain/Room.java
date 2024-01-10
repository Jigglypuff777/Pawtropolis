package pawtropolis.map.domain;

import lombok.RequiredArgsConstructor;
import pawtropolis.animals.domain.Animal;
import pawtropolis.game.domain.Item;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Room {
    private final String name;
    private final Set<Item> items;
    private final Set<Animal> animals;
    private final Map<Direction, Room> adjacentRooms;

    public Optional<Item> getItemByName(String itemName) {
        return items.stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findAny();
    }

    public Room getAdjacentRoomByDirection(Direction direction) {
        return adjacentRooms.get(direction);
    }

    public void putAdjacentRoom(Direction linkedDirection, Room linkedRoom) {
        adjacentRooms.put(linkedDirection, linkedRoom);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public String toString() {
        String itemsString = items.stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "Items: ", "\n"));

        String animalsString = animals.stream()
                .map(animal -> animal.getName().concat("(" + animal.getClass().getSimpleName() + ")"))
                .collect(Collectors.joining(", ", "NPC: ", ""));

        return "You are in " + name + "\n" + itemsString + animalsString;
    }
}