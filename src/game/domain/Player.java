package game.domain;

import java.util.List;

public class Player {
    private static final int DEFAULT_LIFE_POINTS = 20;
    private String name;
    private int lifePoints;
    private final Bag bag;

    public Player(String name){
        this.name = name;
        this.lifePoints = DEFAULT_LIFE_POINTS;
        bag = new Bag();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public Bag getBag() {
        return bag;
    }

    public int getAvailableSlots(){
        return bag.getTotalSlots() - bag.getFilledSlots();
    }

    public void updateAvailableSlots(int newAvailableSlots) {
        bag.setFilledSlots(newAvailableSlots);
    }

    public void getItem(Item item) {
        bag.getItem(item);
    }

    public void dropItem(Item item) {
        bag.dropItem(item);
    }

    public List<Item> getCollectedItems() {
        return bag.getItemList();
    }

}
