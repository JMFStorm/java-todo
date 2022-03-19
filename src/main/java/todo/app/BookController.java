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
public class BookController
{
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository cRepository; 
	
	// VIEWS
	
	@GetMapping("/booklist")
	public String getBooklist(Model model)
	{
		Iterable<Book> books = repository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}
	
	@GetMapping("/addbook")
	public String addBook(Model model)
	{
		model.addAttribute("book", new Book());
		model.addAttribute("categories", cRepository.findAll());
		return "addbook";
	}
	
	@PostMapping("/addbook")
	public String saveBook(Book book)
	{
		repository.save(book);
		return "redirect:booklist";
	}
	
	@GetMapping("/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model)
	{
		Book currentBook = repository.findById(bookId).get();
		model.addAttribute("book", currentBook);
		model.addAttribute("categories", cRepository.findAll());
		return "editbook";
	}
	
	@PostMapping("/editbook/save/{id}")
	public String saveEditBook(@PathVariable("id") Long bookId, Book book)
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
		
		return "redirect:../../booklist";
	}
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model)
	{
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	// REST
	
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest()
    {	
        return (List<Book>) repository.findAll();
    }
    
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId)
    {
    	var x = repository.findById(bookId);
    	return x;
    }   
   
}
