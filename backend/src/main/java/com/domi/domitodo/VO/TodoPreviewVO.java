package com.domi.domitodo.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TodoPreviewVO {
    private int id;
    private String title;
    private LocalDateTime startDate;
}
