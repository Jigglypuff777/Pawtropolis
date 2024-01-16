package pawtropolis;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pawtropolis.game.GameController;

@SpringBootApplication
@RequiredArgsConstructor
public class PawtropolisApplication implements CommandLineRunner {
	private final GameController gameController;

	public static void main(String[] args) {
		SpringApplication.run(PawtropolisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		gameController.runGame();
	}
}
