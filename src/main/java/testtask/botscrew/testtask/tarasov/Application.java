package testtask.botscrew.testtask.tarasov;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import testtask.botscrew.testtask.tarasov.service.ConsoleHandler;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	private final ConsoleHandler handler;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		handler.readQuery();
	}

}
