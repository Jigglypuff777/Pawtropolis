package pawtropolis.game.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pawtropolis.database.service.GameService;
import pawtropolis.game.gamecontroller.GameController;

@RequiredArgsConstructor
@Component
public class ExitCommand implements Command {
    private final GameController gameController;
    private final GameService gameService;
    @Override
    public void execute(String[] inputParts) {
        System.out.println("Pawtropolis hopes to see you again!");
        gameService.save(gameController);
        gameController.setGameEnd(true);
    }
}
