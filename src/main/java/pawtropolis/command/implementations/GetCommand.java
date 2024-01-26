package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.game.domain.Item;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Room;

import java.util.Optional;

@Component
public class GetCommand extends AbstractParametrizedCommand {
    @Autowired
    private GetCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        String selectedItemName = String.join(" ", parameters);
        Room currentRoom = gameController.getCurrentRoom();

        Optional<Item> itemOptional = currentRoom.getItemByName(selectedItemName);
        if (itemOptional.isEmpty()) {
            System.out.println("The selected item is not in the room");
            return;
        }

        Item item = itemOptional.get();
        Player player = gameController.getPlayer();
        if (player.addItem(item)) {
            currentRoom.removeItem(item);
        } else {
            System.out.println("You don't have enough available slots");
        }
    }
}
