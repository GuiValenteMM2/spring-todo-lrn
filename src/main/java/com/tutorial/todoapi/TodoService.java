package com.tutorial.todoapi;

import com.tutorial.todoapi.model.Todo;
import com.tutorial.todoapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo newTodo) {
        if (!newTodo.getDesc().isEmpty() || !newTodo.getName().isEmpty()) {
            if (todoRepository.findAll()
                              .stream().anyMatch(todo -> !todo.getId().equals(newTodo.getId()))) {
                return  this.todoRepository.save(newTodo);
            };
        }
        return newTodo;
    }

    public Todo findTodoById(Long todoId) {
        return this.todoRepository.findById(todoId).orElseThrow();
    }
}
