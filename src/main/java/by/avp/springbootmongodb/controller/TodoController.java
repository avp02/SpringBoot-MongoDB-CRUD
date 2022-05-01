package by.avp.springbootmongodb.controller;

import by.avp.springbootmongodb.model.TodoDTO;
import by.avp.springbootmongodb.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    public ResponseEntity<?> findAllTodos() {
        List<TodoDTO> todos = todoRepository.findAll();

        return (todos.size() > 0) ? new ResponseEntity<>(todos, HttpStatus.OK) :
                new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO) {
        try {
            todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
            return new ResponseEntity<>(todoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodoById(@PathVariable("id") String id) {
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);
        return todoOptional.isPresent() ? new ResponseEntity<>(todoOptional.get(), HttpStatus.OK) :
                new ResponseEntity<>("Not found todo with id = " + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") String id, @RequestBody TodoDTO todoDTO) {
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            TodoDTO todoToSave = todoOptional.get();
            todoToSave.setTodo(todoDTO.getTodo() != null ? todoDTO.getTodo() : todoToSave.getTodo());
            todoToSave.setDescriptions(todoDTO.getDescriptions() != null ? todoDTO.getDescriptions() :
                    todoToSave.getDescriptions());
            todoToSave.setCompleted(todoDTO.getCompleted() != null ? todoDTO.getCompleted() :
                    todoToSave.getCompleted());
            todoToSave.setCreatedAt(todoDTO.getCreatedAt() != null ? todoDTO.getCreatedAt() :
                    todoToSave.getCreatedAt());
            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoToSave);
            return new ResponseEntity<>(todoToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found todo with id = " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") String id) {
        try {
            todoRepository.deleteById(id);
            return new ResponseEntity<>("Todo was deleted successfully with id " + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
