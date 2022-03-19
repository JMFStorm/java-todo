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
	CommandLineRunner commandLineRunner (BookRepository bRepo, CategoryRepository cRepo, UserRepository uRepo)
	{
		return (args) -> {
			
			Category c1 = new Category("Unknown");
			Category c2 = new Category("Thinking about it");
			Category c3 = new Category("Working on it");
			Category c4 = new Category("Not sure if ready");
			Category c5 = new Category("Done, I think...");
			
			cRepo.save(c1);
			cRepo.save(c2);
			cRepo.save(c3);
			cRepo.save(c4);
			cRepo.save(c5);
			
			bRepo.save(new Book("Clean your room", "Janne", "123232-12", 2020, 99.95d, c1));
			bRepo.save(new Book("Do your homework", "Anniina", "334455-12", 2022, 19.95d, c3));
			bRepo.save(new Book("Cook food", "Janne", "143423-92", 2020, 199.95d, c2));
			
			// salasana1, $2a$10$zglslVQzVVoDs7zZyjJvD.4luHBHutb7YyaMoJaop.tV8I1QZ81FS
			User user1 = new User("user", "$2a$10$zglslVQzVVoDs7zZyjJvD.4luHBHutb7YyaMoJaop.tV8I1QZ81FS", "USER");
			
			// salasana2, $2a$10$npkdSVK4nk/98dfSoMwr9u1B2ZiV7aFESeI2p8jtVhC0.T1V17jDO
			User user2 = new User("admin", "$2a$10$npkdSVK4nk/98dfSoMwr9u1B2ZiV7aFESeI2p8jtVhC0.T1V17jDO", "ADMIN");
			
			uRepo.save(user1);
			uRepo.save(user2);
		};
	}
}
