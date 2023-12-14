package projectzoo.game;

import java.util.ArrayList;
import java.util.List;


public class Bag {

    private List<Item> itemList;
    private int availableSlot;

    public Bag(int availableSlot) {
        this.itemList = new ArrayList<>();
        this.availableSlot = availableSlot;
    }

    public int bagUsedSlots() {
        int size = 0;
        for (Item item : itemList) {
            size = item.getSlotRequired() + size;
        }
        return size;
    }


    public void addItem(Item item) {
        if (itemList.size() < availableSlot) {
            itemList.add(item);
        } else {
            System.out.println("The bag is full. Cannot add " + item.getName() + ".");
        }
    }

    public Item findItem(String itemName) {
        for (Item item : itemList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void dropItem(String itemName) {
        boolean itemFound = false;
        for (Item item : itemList) {
            if (item.getName().equals(itemName)) {
                itemFound = true;
                itemList.remove(item);
                break;
            }
        }
        if (!itemFound) {
            System.out.println("Item '" + itemName + "' not found in the bag.");
        }
    }
    public int getAvailableSlot() {
        return availableSlot;
    }
    public List<Item> getItemList() {
        return itemList;
    }

    public void setAvailableSlot(int availableSlot) {
        this.availableSlot = availableSlot;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

}