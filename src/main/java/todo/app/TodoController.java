package todo.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodoController
{
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository cRepository; 
	
	// VIEWS
	
	@GetMapping("/todolist")
	public String getTodolist(Model model)
	{
		Iterable<Book> books = repository.findAll();
		model.addAttribute("books", books);
		return "todolist";
	}
	
	@GetMapping("/addtodo")
	public String addTodo(Model model)
	{
		model.addAttribute("book", new Book());
		model.addAttribute("categories", cRepository.findAll());
		return "addtodo";
	}
	
	@PostMapping("/addtodo")
	public String saveTodo(Book book)
	{
		repository.save(book);
		return "redirect:todolist";
	}
	
	@GetMapping("/edittodo/{id}")
	public String editTodo(@PathVariable("id") Long bookId, Model model)
	{
		Book currentBook = repository.findById(bookId).get();
		model.addAttribute("book", currentBook);
		model.addAttribute("categories", cRepository.findAll());
		return "edittodo";
	}
	
	@PostMapping("/edittodo/save/{id}")
	public String saveEditTodo(@PathVariable("id") Long bookId, Book book)
	{
		if (repository.existsById(bookId))
		{
			Book currentBook = repository.findById(bookId).get();
			currentBook.author = book.author;
			currentBook.title = book.title;
			currentBook.isbn = book.isbn;
			currentBook.year = book.year;
			currentBook.category = book.getCategory();
			
			repository.save(currentBook);
		}
		
		return "redirect:../../todolist";
	}
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTodo(@PathVariable("id") Long bookId, Model model)
	{
		repository.deleteById(bookId);
		return "redirect:../todolist";
	}
	
	// REST
	
    @RequestMapping(value="/todos", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest()
    {	
        return (List<Book>) repository.findAll();
    }
    
    @RequestMapping(value="/todo/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId)
    {
    	var x = repository.findById(bookId);
    	return x;
    }   
   
}
