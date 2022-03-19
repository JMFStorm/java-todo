package koulu.book.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todo.app.Book;
import todo.app.BookRepository;
import todo.app.Category;
import todo.app.CategoryRepository;

import org.junit.jupiter.api.Test;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTests
{
	 @Autowired
	 private BookRepository bRepository;
	 
	 @Autowired
	 private CategoryRepository cRepository;
	 
	 @Test
	 public void createNewBook()
	 {
		 Category category = new Category("Category");
		 Book book = new Book("Title", "Author", "isbn", 2000, 100.0d, category);
		 
		 bRepository.save(book);
		 
		 assertThat(book.getId()).isNotNull();
	 }
	 
	 @Test
	 public void findAllBooks()
	 {
	     Iterable<Book> books = bRepository.findAll();
	     
	     assertThat(books).isNotEmpty();
	 }
	 
	 @Test
	 public void deleteBook()
	 {
		 Optional<Book> book = bRepository.findById((long)3);
		 assertThat(book).isNotNull();
		 
		 bRepository.deleteById((long)3);
		 
		 book = bRepository.findById((long)3);
		 assertThat(book).isEmpty();
	 }
	 
	 @Test
	 public void createNewCategory()
	 {
		 Category category = new Category("Category");
		 
		 cRepository.save(category);
		 
		 assertThat(category.getId()).isNotNull();
	 }
	 
	 @Test
	 public void findAllCategories()
	 {
	     Iterable<Category> categories = cRepository.findAll();
	     
	     assertThat(categories).isNotEmpty();
	 }
	 
	 @Test
	 public void deleteCategory()
	 {
		 Optional<Category> category = cRepository.findById((long)1);
		 assertThat(category).isNotNull();
		 
		 cRepository.deleteById((long)1);
		 
		 category = cRepository.findById((long)1);
		 assertThat(category).isEmpty();
	 }
}
