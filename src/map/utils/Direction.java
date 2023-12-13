package map.utils;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public static Direction getOppositeDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }


}
