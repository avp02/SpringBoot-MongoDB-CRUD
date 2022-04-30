package by.avp.springbootmongodb.controller;

import by.avp.springbootmongodb.model.TodoDTO;
import by.avp.springbootmongodb.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
//    private final TodoRepository todoRepository;

//    public TodoController(TodoRepository todoRepository) {
//        this.todoRepository = todoRepository;
//    }

    @GetMapping("/todos")
    public ResponseEntity<?> findAllTodos() {
        List<TodoDTO> todos = todoRepository.findAll();
        if (todos.size() > 0) {
            return new ResponseEntity<>(todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
        }
    }
}
