package pawtropolis.database.utils;

import org.springframework.stereotype.Component;
import pawtropolis.database.entity.*;
import pawtropolis.game.gamecontroller.GameController;
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

    public RoomEntity fromRoomToEntity(Room room) {
        RoomEntity result = new RoomEntity();
        result.setName(room.getName());
        return result;
    }


//    public RoomEntity fromAdjacentRoomToEntity(Map<DirectionEnum, Room> adjacentsRoom) {
//        RoomEntity result = new RoomEntity();
//
//        for (Map.Entry<DirectionEnum, Room> entry : adjacentsRoom.entrySet()) {
//            DirectionEnum direction = entry.getKey();
//            Room room = entry.getValue();
//            RoomEntity roomEntity = fromRoomToEntity(room);
//            adjacentsRooms.put(direction, roomEntity);
//        }
//        result.setAdjacentRooms(adjacentsRoom);
//
//        return result;
//    }
}



