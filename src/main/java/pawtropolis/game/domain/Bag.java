package pawtropolis.game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Bag {
    private final List<Item> itemList;
    private static final int DEFAULT_AVAILABLE_SLOTS = 10;
    private int availableSlots;

    public Bag(){
        this(DEFAULT_AVAILABLE_SLOTS);
    }
    public Bag(int availableSlots) {
        this.availableSlots = availableSlots;
        this.itemList = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (availableSlots >= item.getRequiredSlots()) {
            itemList.add(item);
            availableSlots -= item.getRequiredSlots();
            return true;
        }
        return false;
    }

    public boolean removeItem(Item item) {
        availableSlots += item.getRequiredSlots();
        return itemList.remove(item);
    }

    public Optional<Item> getItemByName(String itemName) {
        return itemList.stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findAny();
    }

    @Override
    public String toString() {
        return itemList.stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "In bag: ",""));
    }
}
