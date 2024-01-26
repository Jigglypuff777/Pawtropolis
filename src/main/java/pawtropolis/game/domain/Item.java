package pawtropolis.game.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
@EqualsAndHashCode
@Getter
public class Item {
    private final String name;
    private final String description;
    private final int requiredSlots;

    public Item(String name, int requiredSlots) {
        this.name = name;
        this.requiredSlots = requiredSlots;
        this.description = generateItemDescription();
    }

    public String generateItemDescription() {
        return "The " + this.name + " item requires " + this.requiredSlots + " slot";
    }
}