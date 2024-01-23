package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.game.domain.Item;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Room;

import java.util.Optional;

@Component
public class DropCommand extends AbstractParametrizedCommand {
    @Autowired
    private DropCommand(GameController gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        String selectedItemName = String.join(" ", parameters);

        Player player = gameController.getPlayer();
        Optional<Item> itemOptional = player.getItemByName(selectedItemName);
        if (itemOptional.isEmpty()) {
            System.out.println("The selected item is not in the bag");
            return;
        }

        Room currentRoom = gameController.getCurrentRoom();
        Item item = itemOptional.get();
        player.removeItem(item);
        currentRoom.addItem(item);
    }
}
