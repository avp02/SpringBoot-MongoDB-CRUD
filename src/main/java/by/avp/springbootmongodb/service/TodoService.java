package by.avp.springbootmongodb.service;

import by.avp.springbootmongodb.controller.exceptions.TodoCollectionExceptions;
import by.avp.springbootmongodb.model.TodoDTO;

import javax.validation.ConstraintViolationException;

public interface TodoService {

    void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionExceptions;
}
