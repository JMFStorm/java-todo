package todo.app;

import org.springframework.data.repository.CrudRepository;

public interface StateRepository extends CrudRepository<State, Long>
{
}
