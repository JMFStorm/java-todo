package todo.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Todo
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public Long getId()
	{
		return id;
	}
	
	@Column
	public String title;
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	
	public Todo()
	{
	}
	
	public Todo(String title, State state)
	{
		this.title = title;
		this.state = state;
	}
	
	@Override
	public String toString()
	{
		return this.title;
	}
	
	@ManyToOne
	@JoinColumn(name="stateid")
	public State state;
	
	public State getState()
	{
		return state;
	}
	public void setState(State state)
	{
		this.state = state;
	}
}
