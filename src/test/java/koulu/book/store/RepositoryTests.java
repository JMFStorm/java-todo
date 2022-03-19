package koulu.book.store;

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
	 private TodoRepository bRepository;
	 
	 @Autowired
	 private StateRepository cRepository;
	 
	 @Test
	 public void createNewBook()
	 {
		 State category = new State("Category");
		 Todo book = new Todo("Title", "Author", "isbn", 2000, 100.0d, category);
		 
		 bRepository.save(book);
		 
		 assertThat(book.getId()).isNotNull();
	 }
	 
	 @Test
	 public void findAllBooks()
	 {
	     Iterable<Todo> books = bRepository.findAll();
	     
	     assertThat(books).isNotEmpty();
	 }
	 
	 @Test
	 public void deleteBook()
	 {
		 Optional<Todo> book = bRepository.findById((long)3);
		 assertThat(book).isNotNull();
		 
		 bRepository.deleteById((long)3);
		 
		 book = bRepository.findById((long)3);
		 assertThat(book).isEmpty();
	 }
	 
	 @Test
	 public void createNewCategory()
	 {
		 State category = new State("Category");
		 
		 cRepository.save(category);
		 
		 assertThat(category.getId()).isNotNull();
	 }
	 
	 @Test
	 public void findAllCategories()
	 {
	     Iterable<State> categories = cRepository.findAll();
	     
	     assertThat(categories).isNotEmpty();
	 }
	 
	 @Test
	 public void deleteCategory()
	 {
		 Optional<State> category = cRepository.findById((long)1);
		 assertThat(category).isNotNull();
		 
		 cRepository.deleteById((long)1);
		 
		 category = cRepository.findById((long)1);
		 assertThat(category).isEmpty();
	 }
}
