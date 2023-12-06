package game.domain;

public class Item {
    private String name;
    private String description;
    private int requiredSlots;

    public Item(String name, int requiredSlots) {
        this.name = name;
        this.requiredSlots = requiredSlots;
        this.description = generateItemDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequiredSlots() {
        return requiredSlots;
    }

    public void setRequiredSlots(int requiredSlots) {
        this.requiredSlots = requiredSlots;
    }

    public String generateItemDescription() {
        return "The " + this.name + " item requires " + this.requiredSlots + " slot";
    }
}