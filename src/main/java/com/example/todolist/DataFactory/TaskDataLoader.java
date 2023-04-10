package com.example.todolist.DataFactory;

import com.example.todolist.Entity.*;
import com.example.todolist.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TaskDataLoader implements ApplicationRunner {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskDataLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var task1 = new Task("Hacer la compra", "Ir al supermercado", new Date(), new Date(), Priority.LOW, Status.COMPLETED);
        var task2 = new Task("Hacer la compra1", "Ir al supermercado", new Date(), new Date(), Priority.LOW, Status.COMPLETED);
        var task3 = new Task("Hacer la compra2", "Ir al supermercado", new Date(), new Date(), Priority.LOW, Status.COMPLETED);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
    }
}




