package com.domi.domitodo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    User user;

    @Column(nullable = false)
    String title;

    @Column(nullable = false, columnDefinition = "mediumtext")
    String description;

    @Column(nullable = false)
    LocalDateTime startDate;

    @CreatedDate
    LocalDateTime createDate;
}