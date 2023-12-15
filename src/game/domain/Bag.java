package game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {
    private final List<Item> itemList;
    private static final int DEFAULT_AVAILABLE_SLOTS = 10;
    private int availableSlots;

    public Bag(){
        itemList = new ArrayList<>();
        availableSlots = DEFAULT_AVAILABLE_SLOTS;
    }

    public List<Item> getItemList() {
        return itemList;
    }


    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public void getItem(Item item) {
        itemList.add(item);
    }
    public void dropItem(Item item) {
        itemList.remove(item);
    }
    @Override
    public String toString() {
        return this.getItemList().stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "In bag: ",""));
    }
}
