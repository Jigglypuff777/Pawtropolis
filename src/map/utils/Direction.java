package map.utils;

import java.util.Random;

public enum Direction {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private final String label;
    private static final Random random = new Random();

    Direction(String label) {
        this.label = label;
    }

    public static String randomDirection() {
        Direction[] directions = values();
        return directions[random.nextInt(directions.length)].label;
    }



    public static String getOppositeDirection(String directionLabel) {
        switch (directionLabel) {
            case "north":
                return SOUTH.label;
            case "south":
                return NORTH.label;
            case "east":
                return WEST.label;
            case "west":
                return EAST.label;
            default:
                return null;
        }
    }


}
