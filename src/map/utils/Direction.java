package map.utils;

import java.util.Random;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    private static final Random random = new Random();

    // TODO rimuovere??
    Direction() {

    }

    public static Direction randomDirection() {
        Direction[] directions = values();
        return directions[random.nextInt(directions.length)];
    }

    public static Direction getOppositeDirection(String directionLabel) {
        return switch (directionLabel) {
            case "north" -> SOUTH;
            case "south" -> NORTH;
            case "east" -> WEST;
            case "west" -> EAST;
            default -> null;
        };
    }


}
