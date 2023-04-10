package com.example.todolist.Repository;

import com.example.todolist.Entity.Tag;
import com.example.todolist.Entity.Task;
import com.example.todolist.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);

    List<Task> findByTags(Tag tag);

    List<Task> findByDueDateBetween(Date startDate, Date endDate);

    List<Task> findByTitleContainingIgnoreCase(String title);
}
