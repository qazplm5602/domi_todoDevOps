package com.domi.domitodo.service;

import com.domi.domitodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoService {
    private TodoRepository todoRepository;
}
