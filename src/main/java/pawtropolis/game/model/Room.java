package pawtropolis.game.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import pawtropolis.animals.Animal;
import pawtropolis.game.gamecontroller.DirectionEnum;

import java.util.*;

@Data
public class Room {
    private long id;
    private String name;
    private List<Item> items;
    private List<Animal> animals;
    private EnumMap<DirectionEnum, Room> adjacentsRoom;


    public Room(long id, String roomName) {
        this.id = id;
        this.name = roomName;
        this.adjacentsRoom = new EnumMap<>(DirectionEnum.class);
        this.items = new ArrayList<>();
        this.animals = new ArrayList<>();

    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void dropItem(Item item) {
        items.remove(item);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addAdjacents(DirectionEnum direction, Room nextRoom) {
        adjacentsRoom.put(direction, nextRoom);
        nextRoom.adjacentsRoom.put(DirectionEnum.getOppositeDirection(direction), this);
    }

    public Map<DirectionEnum, Room> getAdjacentsRoom() {
        return adjacentsRoom;
    }





}
