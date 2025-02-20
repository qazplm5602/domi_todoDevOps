package com.domi.domitodo.VO;

import com.domi.domitodo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoVO {
    int id;
    String title;
    String description;
    LocalDateTime startDate;

    public static TodoVO toEntity(Todo value) {
        TodoVO result = new TodoVO();

        result.id = value.getId();
        result.title = value.getTitle();
        result.description = value.getDescription();
        result.startDate = value.getStartDate();

        return result;
    }
}
