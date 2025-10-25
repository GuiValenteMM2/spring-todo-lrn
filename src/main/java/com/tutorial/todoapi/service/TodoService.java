package com.tutorial.todoapi.service;

import com.tutorial.todoapi.model.Todo;
import com.tutorial.todoapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void createTodo(Todo newTodo) {
        if (!newTodo.getDesc().isEmpty() || !newTodo.getName().isEmpty()) {
            if (todoRepository.findAll()
                              .stream().anyMatch(todo -> !todo.getId().equals(newTodo.getId()))) {
                this.todoRepository.save(newTodo);
            };
        }
    }

    public Todo findTodoById(Long todoId) {
        return this.todoRepository.findById(todoId).orElseThrow();
    }

    public void updateTodo(Long todoId, String newName, String newDesc) {
        Todo todo = this.findTodoById(todoId);
        this.todoRepository.save(todo);
    }

    public List<Todo> listTodos() {
        return this.todoRepository.findAll();
    }
}
