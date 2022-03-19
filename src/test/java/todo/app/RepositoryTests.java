package todo.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todo.app.Todo;
import todo.app.TodoRepository;
import todo.app.State;
import todo.app.StateRepository;

import org.junit.jupiter.api.Test;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTests
{
	 @Autowired
	 private TodoRepository tRepository;
	 
	 @Autowired
	 private StateRepository sRepository;
	 
	 @Test
	 public void createNewTodo()
	 {
		 State state = new State("Under pressure");
		 Todo todo = new Todo("New task", state);
		 
		 tRepository.save(todo);
		 
		 assertThat(todo.getId()).isNotNull();
	 }
	 
	 @Test
	 public void findAllTodos()
	 {
	     Iterable<Todo> todos = tRepository.findAll();
	     
	     assertThat(todos).isNotEmpty();
	 }
	 
	 @Test
	 public void deleteTodo()
	 {
	     List<Todo> todos = (List<Todo>) tRepository.findAll();
	     long todoId = (long)todos.get(0).id;
	     
		 Optional<Todo> todo = tRepository.findById(todoId);
		 assertThat(todo).isNotNull();
		 
		 tRepository.deleteById(todoId);
		 
		 todo = tRepository.findById(todoId);
		 assertThat(todo).isEmpty();
	 }
	 
	 @Test
	 public void createNewState()
	 {
		 State state = new State("New state");
		 
		 sRepository.save(state);
		 
		 assertThat(state.getId()).isNotNull();
	 }
	 
	 @Test
	 public void findAllStates()
	 {
	     Iterable<State> states = sRepository.findAll();
	     
	     assertThat(states).isNotEmpty();
	 }
	 
	 @Test
	 public void deleteState()
	 {
		 Optional<State> state = sRepository.findById((long)1);
		 assertThat(state).isNotNull();
		 
		 sRepository.deleteById((long)1);
		 
		 state = sRepository.findById((long)1);
		 assertThat(state).isEmpty();
	 }
}
