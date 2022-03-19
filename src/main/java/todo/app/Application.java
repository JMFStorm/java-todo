package todo.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner (TodoRepository tRepo, StateRepository sRepo, UserRepository uRepo)
	{
		return (args) -> {
			
			State c1 = new State("Unknown");
			State c2 = new State("Thinking about it");
			State c3 = new State("Working on it");
			State c4 = new State("Not sure if ready");
			State c5 = new State("Done, I think...");
			
			sRepo.save(c1);
			sRepo.save(c2);
			sRepo.save(c3);
			sRepo.save(c4);
			sRepo.save(c5);
			
			tRepo.save(new Todo("Clean your room", c1));
			tRepo.save(new Todo("Do your homework", c3));
			tRepo.save(new Todo("Cook food", c2));
			
			// salasana1, $2a$10$zglslVQzVVoDs7zZyjJvD.4luHBHutb7YyaMoJaop.tV8I1QZ81FS
			User user1 = new User("user", "$2a$10$zglslVQzVVoDs7zZyjJvD.4luHBHutb7YyaMoJaop.tV8I1QZ81FS", "USER");
			
			// salasana2, $2a$10$npkdSVK4nk/98dfSoMwr9u1B2ZiV7aFESeI2p8jtVhC0.T1V17jDO
			User user2 = new User("admin", "$2a$10$npkdSVK4nk/98dfSoMwr9u1B2ZiV7aFESeI2p8jtVhC0.T1V17jDO", "ADMIN");
			
			uRepo.save(user1);
			uRepo.save(user2);
		};
	}
}
