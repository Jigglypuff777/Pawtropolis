package pawtropolis.database.utils;

import org.springframework.stereotype.Component;
import pawtropolis.animals.Animal;
import pawtropolis.database.entity.*;
import pawtropolis.game.model.Bag;
import pawtropolis.game.model.Item;
import pawtropolis.game.model.Player;
import pawtropolis.game.model.Room;

@Component
public class Converter {

    public PlayerEntity fromPlayerToEntity(Player player, BagEntity bagEntity) {
        PlayerEntity result = new PlayerEntity();
        result.setName(player.getName());
        result.setLifePoints(player.getLifePoints());
        result.setBag(bagEntity);
        return result;
    }

    public BagEntity fromBagToEntity(Bag bag) {
        BagEntity result = new BagEntity();
        result.setAvailableSlots(bag.bagUsedSlots());
        return result;
    }

    public ItemEntity fromItemToEntity(Item item, BagEntity bagEntity) {
        ItemEntity result = new ItemEntity();
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setRequiredSlots(item.getSlotRequired());
        result.setBag(bagEntity);
        return result;
    }

    public ItemEntity fromItemToEntityRoom(Item item, RoomEntity roomEntity){
        ItemEntity result = new ItemEntity();
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setRequiredSlots(item.getSlotRequired());
        result.setRoom(roomEntity);
        return result;
    }

    public AnimalEntity fromAnimalToEntity(Animal animal, RoomEntity roomEntity){
        AnimalEntity result = new AnimalEntity();
        result.setName(animal.getNickname());
        result.setAge(animal.getAge());
        result.setFavouriteFood(animal.getFavoriteFood());
        result.setArrivalDate(animal.getDateEntry());
        result.setWeight(animal.getWeight());
        result.setHeight(animal.getHeight());
        result.setWingspan(result.getWingspan());
        result.setTailLength(result.getTailLength());
        result.setRoom(roomEntity);
        return result;
    }

    public RoomEntity fromRoomToEntity(Room room) {
        RoomEntity result = new RoomEntity();
        result.setName(room.getName());
        return result;
    }



}



