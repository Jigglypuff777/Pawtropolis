package game.domain;

public class Player {
    private String name;
    private final int lifePoints;
    private static final int DEFAULT_LIFE_POINTS = 20;
    private static final String DEFAULT_NAME = "Player One";
    private final Bag bag;

    public Player(String name){
        this.name = name;
        this.lifePoints = DEFAULT_LIFE_POINTS;
        bag = new Bag();
    }
    public Player() {
        this.name = DEFAULT_NAME;
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

    public Bag getBag() {
        return bag;
    }

}
