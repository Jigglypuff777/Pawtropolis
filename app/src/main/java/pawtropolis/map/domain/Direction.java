package pawtropolis.map.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private final String label;
    Direction(String label) {
        this.label = label;
    }

    public static Optional<Direction> getDirectionByString(String string) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.label.equals(string))
                .findFirst();
    }

    public static Direction getOppositeDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }
}
