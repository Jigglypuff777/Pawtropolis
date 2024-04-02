package pawtropolis.game.model;

import lombok.Data;

import java.util.List;

@Data
public class Player {
    private String name;
    private int lifePoints;
    private Bag bag;

    public Player() {
        this.bag = new Bag(30);
    }

    public int bagUsedSlots() {
      return bag.bagUsedSlots();
    }

    public  List<Item> getItems() {
        return bag.getItems();
    }

    public void addItem(Item item) {
            bag.getItems().add(item);
    }

    public void dropItem(Item item){
        bag.dropItem(item);
    }

}


