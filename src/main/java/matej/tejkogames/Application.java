package matej.tejkogames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Online implementation of the Croatian version of the dice game Yahtzee,
 * locally known as Jamb.
 *
 * @author MatejDanic
 * @version 1.0
 * @since 2020-08-13
 */
@ServletComponentScan
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
