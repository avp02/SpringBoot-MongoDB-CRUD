package by.avp.springbootmongodb.service;

import by.avp.springbootmongodb.controller.exceptions.TodoCollectionExceptions;
import by.avp.springbootmongodb.model.TodoDTO;
import by.avp.springbootmongodb.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionExceptions {
        Optional<TodoDTO> todoDTOOptional = todoRepository.findTodoDTOByTodo(todoDTO.getTodo());
        if (todoDTOOptional.isPresent()) {
            throw new TodoCollectionExceptions(TodoCollectionExceptions.todoAlreadyExists());
        }
        todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
        todoRepository.save(todoDTO);
    }
}
