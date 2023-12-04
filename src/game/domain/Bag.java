package game.domain;

import java.util.ArrayList;

public class Bag {
    private List<Item> itemList;
    private final int TOTAL_SLOT = 10;

    public Bag(){
        itemList = new ArrayList<>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public int getTotalSlot() {
        return TOTAL_SLOT;
    }
}
