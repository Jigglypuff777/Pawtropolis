package pawtropolis.command.implementations;

import pawtropolis.game.domain.Item;
import pawtropolis.game.domain.Player;
import pawtropolis.map.domain.Room;

import java.util.List;
import java.util.Optional;

public class DropCommand extends ParametrizedCommand {
    public DropCommand(List<String> parameters) {
        super(parameters);
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
