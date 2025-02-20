package com.domi.domitodo.VO;

import com.domi.domitodo.entity.Todo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoVO {
    int id;
    String title;
    String description;
    LocalDate startDate;

    public static TodoVO toEntity(Todo value) {
        TodoVO result = new TodoVO();

        result.id = value.getId();
        result.title = value.getTitle();
        result.description = value.getDescription();

        return result;
    }
}
