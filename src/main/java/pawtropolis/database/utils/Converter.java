package pawtropolis.database.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import pawtropolis.animals.Animal;
import pawtropolis.animals.TailedAnimal;
import pawtropolis.animals.WingedAnimal;
import pawtropolis.database.entity.*;
import pawtropolis.database.repo.DirectionRepository;
import pawtropolis.game.gamecontroller.DirectionEnum;
import pawtropolis.game.model.Bag;
import pawtropolis.game.model.Item;
import pawtropolis.game.model.Player;
import pawtropolis.game.model.Room;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Data
@Component
public class Converter {

    private final DirectionRepository directionRepository;


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

    public EnumMap<DirectionEnum, Room> getAdjacentRoomsMap(List<AdjacentRoomEntity> adjacentRoomEntities, Map<Long, Room> roomMap) {
        EnumMap<DirectionEnum, Room> adjacentRoomsMap = new EnumMap<>(DirectionEnum.class);

        for (AdjacentRoomEntity adjacentRoomEntity : adjacentRoomEntities) {
            DirectionEntity directionEntity = adjacentRoomEntity.getDirection();
            String directionName = directionEntity.getName(); // Get the direction name
            DirectionEnum directionEnum = DirectionEnum.parseDirection(directionName); // Parse DirectionEnum from name

            long roomId = adjacentRoomEntity.getRoom().getId();
            long adjacentRoomId = adjacentRoomEntity.getAdjacentRoom().getId();

            Room currentRoom = roomMap.get(roomId);
            Room adjacentRoom = roomMap.get(adjacentRoomId);

            adjacentRoomsMap.put(directionEnum, adjacentRoom);
            currentRoom.addAdjacents(directionEnum, adjacentRoom);
        }

        return adjacentRoomsMap;
    }



//    public Room fromEntityToRoom(RoomEntity roomEntity) {
//        long resultId = roomEntity.getId();
//        String resultName = roomEntity.getName();
//        Room room = new Room(resultId, resultName);
//
//        // Converti gli oggetti AnimalEntity in Animal e aggiungili alla lista
//        for (AnimalEntity animalEntity : roomEntity.getAnimals()) {
//            Animal animal = fromEntityToAnimal(animalEntity);
//            room.getAnimals().add(animal);
//        }
//
//        // Converti gli oggetti ItemEntity in Item e aggiungili alla lista
//        for (ItemEntity itemEntity : roomEntity.getItems()) {
//            Item item = fromEntityToItem(itemEntity);
//            room.getItems().add(item);
//        }
//
//        return room;
//    }


    public Room fromEntityToRoom(RoomEntity roomEntity, List<ItemEntity> itemEntities, List<AnimalEntity> animalEntities, List<AdjacentRoomEntity> adjacentRoomEntities, Map<Long, Room> roomMap) {
        long resultId = roomEntity.getId();
        String resultName = roomEntity.getName();

        List<Item> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities) {
            items.add(fromEntityToItem(itemEntity));
        }

        List<Animal> animals = new ArrayList<>();
        for (AnimalEntity animalEntity : animalEntities) {
            animals.add(fromEntityToAnimal(animalEntity));
        }

        EnumMap<DirectionEnum, Room> adjacentRooms = getAdjacentRoomsMap(adjacentRoomEntities, roomMap);

        Room room = new Room(resultId, resultName);
        room.setItems(items);
        room.setAnimals(animals);
        room.setAdjacentsRoom(adjacentRooms);

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



