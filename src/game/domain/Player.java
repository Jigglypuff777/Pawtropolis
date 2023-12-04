package game.domain;

public class Player {
    private final String name;
    private final int lifePoints;
    private final int DEFAULT_LIFE_POINTS = 20;
    private final Bag bag;

    public Player(String name){
        this.name = name;
        this.lifePoints = DEFAULT_LIFE_POINTS;
        bag = new Bag();
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public Bag getBag() {
        return bag;
    }

}
