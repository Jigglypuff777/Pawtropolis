package pawtropolis.command.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.GameController;
import pawtropolis.game.domain.Item;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Room;
import pawtropolis.map.utils.MapController;

import java.util.Optional;

@Component
public class DropCommand extends AbstractParametrizedCommand {
    private final MapController mapController;
    @Autowired
    private DropCommand(GameController gameController, MapController mapController) {
        super(gameController);
        this.mapController = mapController;
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

        Room currentRoom = mapController.getCurrentRoom();
        Item item = itemOptional.get();
        player.removeItem(item);
        currentRoom.addItem(item);
    }
}
