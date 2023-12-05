package map.domain;

import animals.domain.Animal;
import animals.utils.AnimalFactory;
import game.domain.Item;
import map.utils.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class Room {
    private String name;
    private List<Item> currentRoomItems;
    private Set<Animal> currentRoomAnimals;
    private Map<String, Room> adjacentRooms;

    public Room(String name, List<Item> currentRoomItems, Set<Animal> currentRoomAnimals, Map<String, Room> adjacentRooms) {
        this.name = name;
        this.currentRoomItems = currentRoomItems;
        this.currentRoomAnimals = currentRoomAnimals;
        this.adjacentRooms = adjacentRooms;
    }

    public Room() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getCurrentRoomItems() {
        return currentRoomItems;
    }

    public void setItems(List<Item> items) {
        this.currentRoomItems = items;
    }

    public Set<Animal> getCurrentRoomAnimals() {
        return currentRoomAnimals;
    }

    public void setCurrentRoomAnimals(Set<Animal> currentRoomAnimals) {
        this.currentRoomAnimals = currentRoomAnimals;
    }

    public Map<String, Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void setAdjacentRooms(Map<String, Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }

    public static Room createRandomRoom(Room previousRoom, String previousDirection) {
        Room newCurrentRoom = new Room();
        Random random = new Random();
        newCurrentRoom.setName("Room" + random.nextInt(100));
        newCurrentRoom.setCurrentRoomAnimals(new AnimalFactory().getRandomAnimalsSet(3));
        //TODO item
        newCurrentRoom.setItems(new ArrayList<>(Arrays.asList((new Item("coltello", "descrizione", 1)))));
        Map<String, Room> newMap = new HashMap<>();
        newMap.put(Direction.getOppositeDirection(previousDirection), previousRoom);
        int additionalDoors = random.nextInt(4);
        for (int i = 0; i < additionalDoors; i++) {
            newMap.putIfAbsent(Direction.randomDirection(), null);
        }
        newCurrentRoom.setAdjacentRooms(newMap);
        return newCurrentRoom;
    }


    public static Room createRandomRoom() {
        Room newCurrentRoom = new Room();
        Random random = new Random();
        newCurrentRoom.setName("Room" + random.nextInt(100));
        newCurrentRoom.setCurrentRoomAnimals(new AnimalFactory().getRandomAnimalsSet(3));
        //TODO item
        newCurrentRoom.setItems(new ArrayList<>(Arrays.asList((new Item("coltello", "descrizione", 1)))));
        Map<String, Room> newMap = new HashMap<>();

        int additionalDoors = random.nextInt(1,4);
        for (int i = 0; i < additionalDoors; i++) {
            newMap.putIfAbsent(Direction.randomDirection(), null);
        }
        newCurrentRoom.setAdjacentRooms(newMap);
        return newCurrentRoom;
    }

    @Override
    public String toString() {
        String currentRoomItemsString = this.getCurrentRoomItems().stream()
                .map(Item::getName)
                .collect(Collectors.joining(", ", "Items: ", "\n"));

        String currentRoomAnimalsString = this.getCurrentRoomAnimals().stream()
                .map(animal -> animal.getName().concat("(" + animal.getClass().getSimpleName() + ")"))
                .collect(Collectors.joining(", ", "NPC: ", ""));

        return "You are in " + this.getName() + "\n" + currentRoomItemsString + currentRoomAnimalsString;
    }
}