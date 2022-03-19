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
	private TodoRepository repository;
	
	@Autowired
	private StateRepository cRepository; 
	
	// VIEWS
	
	@GetMapping("/todolist")
	public String getTodolist(Model model)
	{
		Iterable<Todo> books = repository.findAll();
		model.addAttribute("books", books);
		return "todolist";
	}
	
	@GetMapping("/addtodo")
	public String addTodo(Model model)
	{
		model.addAttribute("book", new Todo());
		model.addAttribute("categories", cRepository.findAll());
		return "addtodo";
	}
	
	@PostMapping("/addtodo")
	public String saveTodo(Todo book)
	{
		repository.save(book);
		return "redirect:todolist";
	}
	
	@GetMapping("/edittodo/{id}")
	public String editTodo(@PathVariable("id") Long bookId, Model model)
	{
		Todo currentBook = repository.findById(bookId).get();
		model.addAttribute("book", currentBook);
		model.addAttribute("categories", cRepository.findAll());
		return "edittodo";
	}
	
	@PostMapping("/edittodo/save/{id}")
	public String saveEditTodo(@PathVariable("id") Long bookId, Todo book)
	{
		if (repository.existsById(bookId))
		{
			Todo currentBook = repository.findById(bookId).get();
			currentBook.author = book.author;
			currentBook.title = book.title;
			currentBook.isbn = book.isbn;
			currentBook.year = book.year;
			currentBook.state = book.getState();
			
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
    public @ResponseBody List<Todo> bookListRest()
    {	
        return (List<Todo>) repository.findAll();
    }
    
    @RequestMapping(value="/todo/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Todo> findBookRest(@PathVariable("id") Long bookId)
    {
    	var x = repository.findById(bookId);
    	return x;
    }   
   
}
