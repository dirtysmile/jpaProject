package project.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.jpa.initData.DataService;

@EnableJpaAuditing
@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@RestController
	private static class SampleController {
		@RequestMapping("/")
		public String index(){
			return "Hello, World";
		}
	}

	@Bean
	public CommandLineRunner test(DataService dataService){
		return args -> {
			dataService.insertUsers();
		};
	}
}
