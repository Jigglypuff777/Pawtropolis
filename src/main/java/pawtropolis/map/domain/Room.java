package pawtropolis.map.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pawtropolis.animals.domain.Animal;
import pawtropolis.game.domain.Item;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Room {
    private final String name;
    private final Set<Item> items;
    private final Set<Animal> animals;
    private final Map<Direction, Door> doors;

    public Optional<Item> getItemByName(String itemName) {
        return items.stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findAny();
    }

    public void putDoor(Direction linkedDirection, Door door) {
        doors.put(linkedDirection, door);
    }

    public Door getDoorByDirection(Direction direction) {
        return doors.get(direction);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }
    public Item getRandomItem() {
        return items.stream()
                .skip(ThreadLocalRandom.current().nextInt(items.size()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        String itemsString = items.stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "Items: ", "\n"));

        String animalsString = animals.stream()
                .map(animal -> animal.getName().concat("(" + animal.getClass().getSimpleName() + ")"))
                .collect(Collectors.joining(", ", "NPC: ", "\n"));

        String adjacentRoomString = doors.entrySet().stream()
                .map(entry -> entry.getKey().toString().concat("(" + (entry.getValue().isLocked()? "locked" : "unlocked") + ")"))
                .collect(Collectors.joining(", ", "Doors: ", ""));

        return "You are in " + name + "\n" + itemsString + animalsString + adjacentRoomString;
    }
}