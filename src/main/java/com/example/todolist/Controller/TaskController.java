package com.example.todolist.Controller;

import com.example.todolist.Entity.*;
import com.example.todolist.Service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.data.crossstore.ChangeSetPersister.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) throws NotFoundException {
        return taskService.getTaskById(id);
    }

    @PostMapping("/")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) throws NotFoundException {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) throws NotFoundException {
        taskService.deleteTask(id);
    }

    @GetMapping("/user/{userId}")
    public List<Task> findTasksByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return taskService.findTasksByUser(user);
    }

    @GetMapping("/tag/{tagId}")
    public List<Task> findTasksByTag(@PathVariable Long tagId) {
        Tag tag = new Tag();
        tag.setId(tagId);
        return taskService.findTasksByTag(tag);
    }

    @GetMapping("/due-date")
    public List<Task> findTasksByDueDateBetween(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return taskService.findTasksByDueDateBetween(startDate, endDate);
    }

    @GetMapping("/search")
    public List<Task> findTasksByTitle(@RequestParam("title") String title) {
        return taskService.findTasksByTitle(title);
    }
}

