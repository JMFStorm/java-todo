package todo.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todo.app.TodoController;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests
{
	
	@Autowired
	private TodoController tController;

	@Test
	public void contextLoads()
	{
		assertThat(tController).isNotNull();
	}
}
