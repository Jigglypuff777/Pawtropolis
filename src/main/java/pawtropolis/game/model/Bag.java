package pawtropolis.game.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class Bag {
    private long id;
    private List<Item> items;
    private int slot;

    public Bag(long id, int slot) {
        this.id = id;
        this.items = new ArrayList<>();
        this.slot = slot;
    }

    public int bagUsedSlots() {
        return items.stream()
                .mapToInt(Item::getSlotRequired)
                .sum();
    }


    public void dropItem(Item item) {
        items.remove(item);
    }


}
