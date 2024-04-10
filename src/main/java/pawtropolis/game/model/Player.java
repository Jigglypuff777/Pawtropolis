package pawtropolis.game.model;

import lombok.Data;

import java.util.List;

@Data
public class Player {
    private long id;
    private String name;
    private int lifePoints;
    private Bag bag;

    public Player(long id, String name, int lifePoints) {
        this.id = id;
        this.name = name;
        this.lifePoints = lifePoints;
    }

    public int bagUsedSlots() {
        return bag.bagUsedSlots();
    }

    public List<Item> getItems() {
        return bag.getItems();
    }

    public void addItem(Item item) {
        bag.getItems().add(item);
    }

    public void dropItem(Item item) {
        bag.dropItem(item);
    }

}


