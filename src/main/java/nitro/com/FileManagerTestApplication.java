package nitro.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@SpringBootApplication


public class FileManagerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagerTestApplication.class, args);
		FreeMarkerViewResolver freeMarkerViewResolver=new FreeMarkerViewResolver();

	}
}
