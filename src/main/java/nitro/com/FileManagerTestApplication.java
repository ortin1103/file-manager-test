package nitro.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("nitro.com")
//@EntityScan({"nitro.com.Entity;"})

public class FileManagerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagerTestApplication.class, args);
	}
}
