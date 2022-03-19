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
	private TodoRepository tRepository;
	
	@Autowired
	private StateRepository sRepository; 
	
	// VIEWS
	
	@GetMapping("/todolist")
	public String getTodolist(Model model)
	{
		Iterable<Todo> todos = tRepository.findAll();
		model.addAttribute("todos", todos);
		return "todolist";
	}
	
	@GetMapping("/addtodo")
	public String addTodo(Model model)
	{
		model.addAttribute("todo", new Todo());
		model.addAttribute("states", sRepository.findAll());
		return "addtodo";
	}
	
	@PostMapping("/addtodo")
	public String saveTodo(Todo todo)
	{
		tRepository.save(todo);
		return "redirect:todolist";
	}
	
	@GetMapping("/edittodo/{id}")
	public String editTodo(@PathVariable("id") Long todoId, Model model)
	{
		Todo currentTodo = tRepository.findById(todoId).get();
		model.addAttribute("todo", currentTodo);
		model.addAttribute("states", sRepository.findAll());
		return "edittodo";
	}
	
	@PostMapping("/edittodo/save/{id}")
	public String saveEditTodo(@PathVariable("id") Long bookId, Todo book)
	{
		if (tRepository.existsById(bookId))
		{
			Todo currentBook = tRepository.findById(bookId).get();
			currentBook.title = book.title;
			currentBook.state = book.getState();
			
			tRepository.save(currentBook);
		}
		
		return "redirect:../../todolist";
	}
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTodo(@PathVariable("id") Long todoId, Model model)
	{
		tRepository.deleteById(todoId);
		return "redirect:../todolist";
	}
	
	// REST
	
    @RequestMapping(value="/todos", method = RequestMethod.GET)
    public @ResponseBody List<Todo> todoListRest()
    {	
        return (List<Todo>) tRepository.findAll();
    }
    
    @RequestMapping(value="/todo/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Todo> findTodoRest(@PathVariable("id") Long todoId)
    {
    	var x = tRepository.findById(todoId);
    	return x;
    }   
   
}
