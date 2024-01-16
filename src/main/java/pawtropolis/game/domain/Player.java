package pawtropolis.game.domain;

import lombok.Getter;

import java.util.Optional;

public class Player {
    private static final int DEFAULT_LIFE_POINTS = 20;
    @Getter
    private final String name;
    private int lifePoints;
    private final Bag bag;

    public Player(String name){
        this(name, DEFAULT_LIFE_POINTS);
    }

    public Player(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
        bag = new Bag();
    }

    public String getBagDescription() {
        return bag.toString();
    }

    public Optional<Item> getItemByName(String itemName) {
        return bag.getItemByName(itemName);
    }

    public boolean addItem(Item item) {
        return bag.addItem(item);
    }

    public boolean removeItem(Item item) {
        return bag.removeItem(item);
    }

}
