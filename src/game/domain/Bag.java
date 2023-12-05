package game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {
    private List<Item> itemList;
    private final int TOTAL_SLOTS = 10;
    private int filledSlots;

    public Bag(){
        itemList = new ArrayList<>();
        filledSlots = 0;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public int getTotalSlots() {
        return TOTAL_SLOTS;
    }

    public int getFilledSlots() {
        return filledSlots;
    }

    public void setFilledSlots(int filledSlots) {
        this.filledSlots = filledSlots;
    }

    @Override
    public String toString() {
        return this.getItemList().stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "In bag: ", "."));
    }
}
