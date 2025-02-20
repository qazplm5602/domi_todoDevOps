package com.domi.domitodo.service;

import com.domi.domitodo.DTO.TodoFormDTO;
import com.domi.domitodo.entity.Todo;
import com.domi.domitodo.entity.User;
import com.domi.domitodo.exception.TodoException;
import com.domi.domitodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo getTodoById(int id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoException(TodoException.Type.NOT_FOUND));
    }

    public Todo getTodoByIdVerify(int id, User user) {
        Todo currentTodo = getTodoById(id);

        if (currentTodo.getUser() != user) // 주인 아닌뎅
            throw new TodoException(TodoException.Type.NEED_PERMISSION);

        return currentTodo;
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

    public void editTodo(User user, int id, TodoFormDTO form) {
        Todo currentTodo = getTodoByIdVerify(id, user);

        currentTodo.setTitle(form.getTitle());
        currentTodo.setDescription(form.getDesc());
        currentTodo.setStartDate(LocalDateTime.parse(form.getDate()));

        todoRepository.save(currentTodo);
    }

    public void removeTodo(User user, int id) {
        Todo todo = getTodoByIdVerify(id, user);
        todoRepository.delete(todo);
    }

    public List<Todo> getTodayList(User user) {
        LocalDate today = LocalDate.now();
        LocalDateTime startDate = today.atStartOfDay();
        LocalDateTime endDate = startDate.plusDays(1).minusNanos(1);


        return todoRepository.findByUserAndStartDateIsBetweenOrderByStartDateDesc(user, startDate, endDate);
    }
}
