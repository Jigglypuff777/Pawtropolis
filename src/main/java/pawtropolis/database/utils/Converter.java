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

import java.util.*;
import java.util.stream.Collectors;

@Data
@Component
public class Converter {

    private final DirectionRepository directionRepository;


    public PlayerEntity fromPlayerToEntity(Player player, BagEntity bagEntity) {
        PlayerEntity result = new PlayerEntity();
        result.setId(player.getId());
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
        result.setId(bag.getId());
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
        result.setId(item.getId());
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

    public ArrayList<Item> fromEntityToItemList(List<ItemEntity> itemEntities) {
        return itemEntities
                .stream()
                .map(this::fromEntityToItem)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public ItemEntity fromItemToEntityRoom(Item item, RoomEntity roomEntity) {
        ItemEntity result = new ItemEntity();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setRequiredSlots(item.getSlotRequired());
        result.setRoom(roomEntity);
        return result;
    }

    public AnimalEntity fromAnimalToEntity(Animal animal, RoomEntity roomEntity) {
        AnimalEntity result = new AnimalEntity();
        result.setId(animal.getId());
        result.setName(animal.getNickname());
        result.setAge(animal.getAge());
        result.setFavouriteFood(animal.getFavoriteFood());
        result.setArrivalDate(animal.getDateEntry());
        result.setWeight(animal.getWeight());
        result.setHeight(animal.getHeight());
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
        result.setId(room.getId());
        result.setName(room.getName());
        return result;
    }

    public EnumMap<DirectionEnum, Room> fromEntityListToAdjacentRoomMap(List<AdjacentRoomEntity> adjacentRoomEntities) {
        EnumMap<DirectionEnum, Room> adjacentRoomsMap = new EnumMap<>(DirectionEnum.class);

        adjacentRoomEntities.forEach(adjacentRoomEntity -> {
            DirectionEntity directionEntity = adjacentRoomEntity.getDirection();
            String directionName = directionEntity.getName(); // Get the direction name
            DirectionEnum directionEnum = DirectionEnum.parseDirection(directionName); // Parse DirectionEnum from name

            Room adjacentRoom = fromEntityToRoom(adjacentRoomEntity.getAdjacentRoom());

            adjacentRoomsMap.put(directionEnum, adjacentRoom);
        });

        return adjacentRoomsMap;
    }

    public Room fromEntityToRoom(RoomEntity roomEntity) {
        long resultId = roomEntity.getId();
        String resultName = roomEntity.getName();
        return new Room(resultId, resultName);
    }

    public Animal fromEntityToAnimal(AnimalEntity animalEntity) {
        return new Animal(
                animalEntity.getId(),
                animalEntity.getName(),
                animalEntity.getFavouriteFood(),
                animalEntity.getAge(),
                animalEntity.getArrivalDate(),
                animalEntity.getWeight(),
                animalEntity.getHeight()
        );
    }

    public List<Animal> fromEntityListToAnimalList(List<AnimalEntity> animalEntities) {
        return animalEntities
                .stream()
                .map(this::fromEntityToAnimal)
                .toList();
    }

    public DirectionEnum fromEntityToEnum(DirectionEntity directionEntity) {
        String directionName = directionEntity.getName();
        return DirectionEnum.valueOf(directionName.toUpperCase());
    }

}



