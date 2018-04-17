package com.example.demo.service;

import com.example.demo.domain.Task;
import com.example.demo.repository.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task createTask(Task task) {
    return taskRepository.save(task);
  }

  public Task getTaskById(Long id){
    return taskRepository.findOne(id);
  }

  public void deleteTask(Long id) {
    taskRepository.delete(id);
  }

  public List<Task> getAllTasks(){
    return taskRepository.findAll();
  }
}
