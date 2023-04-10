package com.example.todolist.Service;

import com.example.todolist.Entity.Tag;
import com.example.todolist.Entity.Task;
import com.example.todolist.Entity.User;
import com.example.todolist.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /*public Task getTaskById(Long id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new NotFoundException();
        }
    }*/
    public Task getTaskById(Long id) throws NotFoundException {
        return taskRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /*public Task updateTask(Long id, Task task) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            task.setId(id);
            return taskRepository.save(task);
        } else {
            throw new NotFoundException();
        }
    }*/

    public Task updateTask(Long id, Task task) throws NotFoundException {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    task.setId(id);
                    return taskRepository.save(task);
                })
                .orElseThrow(NotFoundException::new);
    }

    /*public void deleteTask(Long id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.delete(optionalTask.get());
        } else {
            throw new NotFoundException();
        }
    }*/
    public void deleteTask(Long id)  {
        taskRepository.findById(id)
                .ifPresentOrElse(taskRepository::delete, NotFoundException::new);
    }


    public List<Task> findTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public List<Task> findTasksByTag(Tag tag) {
        return taskRepository.findByTags(tag);
    }

    public List<Task> findTasksByDueDateBetween(Date startDate, Date endDate) {
        return taskRepository.findByDueDateBetween(startDate, endDate);
    }

    public List<Task> findTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
}
