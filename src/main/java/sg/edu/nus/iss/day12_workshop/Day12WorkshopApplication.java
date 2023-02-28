package sg.edu.nus.iss.day12_workshop;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://attacomsian.com/blog/thymeleaf-iterate-map-list-set-array


@SpringBootApplication
public class Day12WorkshopApplication {

	private static String portNum = null;

	public static void main(String[] args) {

		if (portNum == null || portNum.length() == 0) {
			portNum = System.getenv("PORT");
		}

		SpringApplication app = new SpringApplication(Day12WorkshopApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", portNum));
		app.run(args);
	}

}
