package pawtropolis.map.domain;

import lombok.Getter;
import lombok.Setter;
import pawtropolis.game.domain.Item;

import java.util.Random;

@Getter
@Setter
public class Door {

    private static final Random random = new Random();
    private Room originRoom;
    private Room destinationRoom;
    private boolean isLocked;
    private Item unlockingItem;

    public Door(Room originRoom, Room destinationRoom) {
        this.originRoom = originRoom;
        this.destinationRoom = destinationRoom;
        isLocked = random.nextBoolean();
        unlockingItem = getRandomUnlockingItem(originRoom);
    }


    public void swapRooms() {
        Room temp = this.destinationRoom;
        this.destinationRoom = this.originRoom;
        this.originRoom = temp;
    }

    private Item getRandomUnlockingItem(Room originRoom) {
        return originRoom.getRandomItem();
    }

}
