package com.domi.domitodo.controller;

import com.domi.domitodo.DTO.TodoFormDTO;
import com.domi.domitodo.VO.TodoPreviewVO;
import com.domi.domitodo.VO.TodoVO;
import com.domi.domitodo.entity.Todo;
import com.domi.domitodo.entity.User;
import com.domi.domitodo.exception.TodoException;
import com.domi.domitodo.service.TodoService;
import com.domi.domitodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/todo")
@RequiredArgsConstructor
@RestController
public class TodoController {
    private final UserService userService;
    private final TodoService todoService;

    @GetMapping("/{id}")
    TodoVO getTodoContent(@PathVariable int id) {
        User user = userService.getCurrentUser();
        Todo todo = todoService.getTodoById(id);

        // 다른 사람꺼인데??
        if (todo.getUser() != user)
            throw new TodoException(TodoException.Type.NEED_PERMISSION);

        return TodoVO.toEntity(todo);
    }

    @PutMapping("/")
    int createTodo(@RequestBody TodoFormDTO form) {
        User user = userService.getCurrentUser();
        return todoService.createTodo(user, form);
    }

    @PostMapping("/{id}")
    void editTodo(@PathVariable("id") int id, @RequestBody TodoFormDTO form) {
        User user = userService.getCurrentUser();
        todoService.editTodo(user, id, form);
    }

    @DeleteMapping("/{id}")
    void removeTodo(@PathVariable("id") int id) {
        User user = userService.getCurrentUser();
        todoService.removeTodo(user, id);
    }

    @GetMapping("/today")
    List<TodoPreviewVO> getTodayList() {
        User user = userService.getCurrentUser();

        return todoService.getTodayList(user)
                .stream()
                .map(todo -> new TodoPreviewVO(todo.getId(), todo.getTitle(), todo.getStartDate()))
                .collect(Collectors.toList());
    }
}
