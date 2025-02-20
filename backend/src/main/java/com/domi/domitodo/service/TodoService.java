package com.domi.domitodo.service;

import com.domi.domitodo.entity.Todo;
import com.domi.domitodo.exception.TodoException;
import com.domi.domitodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo getTodoById(int id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoException(TodoException.Type.NOT_FOUND));
    }
}
