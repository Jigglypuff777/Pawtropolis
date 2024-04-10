package pawtropolis.database.utils;

import org.springframework.stereotype.Component;
import pawtropolis.animals.Animal;
import pawtropolis.animals.TailedAnimal;
import pawtropolis.animals.WingedAnimal;
import pawtropolis.database.entity.*;
import pawtropolis.game.model.Bag;
import pawtropolis.game.model.Item;
import pawtropolis.game.model.Player;
import pawtropolis.game.model.Room;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {

    public PlayerEntity fromPlayerToEntity(Player player, BagEntity bagEntity) {
        PlayerEntity result = new PlayerEntity();
        result.setName(player.getName());
        result.setLifePoints(player.getLifePoints());
        result.setBag(bagEntity);
        return result;
    }

    public Player fromEntityToPlayer(PlayerEntity playerEntity) {
        long resultId = playerEntity.getId();
        String resultName = playerEntity.getName();
        int resultLifePoints = playerEntity.getLifePoints();
        return new Player(resultId, resultName, resultLifePoints);
    }

    public BagEntity fromBagToEntity(Bag bag) {
        BagEntity result = new BagEntity();
        result.setOccupiedSlots(bag.bagUsedSlots());
        return result;
    }

    public Bag fromEntityToBag(BagEntity bagEntity, List<ItemEntity> itemEntities) {
        long resultId = bagEntity.getId();
        int resultOccupiedSlots = bagEntity.getOccupiedSlots();
        Bag bag = new Bag(resultId, 30);

        List<Item> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities) {
            items.add(fromEntityToItem(itemEntity));
        }

        bag.setItems(items);
        return bag;
    }

    public ItemEntity fromItemToEntity(Item item, BagEntity bagEntity) {
        ItemEntity result = new ItemEntity();
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setRequiredSlots(item.getSlotRequired());
        result.setBag(bagEntity);
        return result;
    }

    public Item fromEntityToItem(ItemEntity itemEntity) {
        Item item = new Item();
        item.setId(itemEntity.getId());
        item.setName(itemEntity.getName());
        item.setDescription(itemEntity.getDescription());
        item.setSlotRequired(itemEntity.getRequiredSlots());
        return item;
    }

    public ItemEntity fromItemToEntityRoom(Item item, RoomEntity roomEntity) {
        ItemEntity result = new ItemEntity();
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setRequiredSlots(item.getSlotRequired());
        result.setRoom(roomEntity);
        return result;
    }

    public AnimalEntity fromAnimalToEntity(Animal animal, RoomEntity roomEntity) {
        AnimalEntity result = new AnimalEntity();
        result.setName(animal.getNickname());
        result.setAge(animal.getAge());
        result.setFavouriteFood(animal.getFavoriteFood());
        result.setArrivalDate(animal.getDateEntry());
        result.setWeight(animal.getWeight());
        result.setHeight(animal.getHeight());
        //TODO:IMPLEMENTARE SPECIES
        if (animal instanceof WingedAnimal wingedAnimal) {
            result.setWingspan(wingedAnimal.getWingspan());
        } else if (animal instanceof TailedAnimal tailedAnimal) {
            result.setTailLength(tailedAnimal.getTailLength());
        }

        result.setTailLength(result.getTailLength());
        result.setRoom(roomEntity);
        return result;
    }


    public RoomEntity fromRoomToEntity(Room room) {
        RoomEntity result = new RoomEntity();
        result.setName(room.getName());
        return result;
    }

    public Room fromEntityToRoom(RoomEntity roomEntity, List<ItemEntity> itemEntities, List<AnimalEntity> animalEntities) {
        long resultId = roomEntity.getId();
        String resultName = roomEntity.getName();

        List<Item> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities) {
            items.add(fromEntityToItem(itemEntity));
        }

        List<Animal> animals = new ArrayList<>();
        for (AnimalEntity animalEntitie : animalEntities) {
            animals.add(fromEntityToAnimal(animalEntitie));
        }

        Room room = new Room(resultId, resultName);
        room.setItems(items);
        room.setAnimals(animals);

        return room;
    }

    private Animal fromEntityToAnimal(AnimalEntity animalEntities) {
        return new Animal(
                animalEntities.getId(),
                animalEntities.getName(),
                animalEntities.getFavouriteFood(),
                animalEntities.getAge(),
                animalEntities.getArrivalDate(),
                animalEntities.getWeight(),
                animalEntities.getHeight()
        );

    }


}



