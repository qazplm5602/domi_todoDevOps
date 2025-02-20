package com.domi.domitodo.repository;

import com.domi.domitodo.entity.Todo;
import com.domi.domitodo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUserAndStartDateIsBetweenOrderByStartDateDesc(User user, LocalDateTime startDateBefore, LocalDateTime startDateAfter);
}
