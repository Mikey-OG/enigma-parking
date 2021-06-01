package Enigma.ParkingProject;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ParkingProjectApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ParkingProjectApplication.class);
		builder.headless(false).run(args);
		//(exclude = {SecurityAutoConfiguration.class })
	}

}
