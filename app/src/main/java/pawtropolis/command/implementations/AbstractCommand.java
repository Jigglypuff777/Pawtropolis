package pawtropolis.command.implementations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pawtropolis.game.GameController;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCommand implements Command {
    protected final GameController gameController;
}