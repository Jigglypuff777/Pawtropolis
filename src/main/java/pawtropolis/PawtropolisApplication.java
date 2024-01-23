package pawtropolis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pawtropolis.game.GameController;

@SpringBootApplication
public class PawtropolisApplication implements CommandLineRunner {
	private final GameController gameController;

	@Autowired
	public PawtropolisApplication(GameController gameController) {
		this.gameController = gameController;
	}

	public static void main(String[] args) {
		SpringApplication.run(PawtropolisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		gameController.runGame();
	}
}
