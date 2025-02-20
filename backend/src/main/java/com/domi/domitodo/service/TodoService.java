package com.domi.domitodo.service;

import com.domi.domitodo.DTO.TodoFormDTO;
import com.domi.domitodo.entity.Todo;
import com.domi.domitodo.entity.User;
import com.domi.domitodo.exception.TodoException;
import com.domi.domitodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo getTodoById(int id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoException(TodoException.Type.NOT_FOUND));
    }

    public int createTodo(User owner, TodoFormDTO form) {
        Todo newTodo = new Todo();
        newTodo.setUser(owner);
        newTodo.setTitle(form.getTitle());
        newTodo.setDescription(form.getDesc());
        newTodo.setStartDate(LocalDateTime.parse(form.getDate()));

        Todo todo = todoRepository.save(newTodo);
        return todo.getId();
    }
}
