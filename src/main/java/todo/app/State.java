package todo.app;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class State
{
	public State()
	{
	}
	
	public State(String name)
	{
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public Long getId()
	{
		return id;
	}
	
	@Column
	public String name;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="state")
	public List<Todo> todos;
	
	public List<Todo> getTodos()
	{
		return todos;
	}
	public void setTodos(List<Todo> todos)
	{
		this.todos = todos;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}
