package pawtropolis.game.domain;

public class Item {
    private final String name;
    private final String description;
    private final int requiredSlots;

    public Item(String name, int requiredSlots) {
        this.name = name;
        this.requiredSlots = requiredSlots;
        this.description = generateItemDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredSlots() {
        return requiredSlots;
    }

    public String generateItemDescription() {
        return "The " + this.name + " item requires " + this.requiredSlots + " slot";
    }
}