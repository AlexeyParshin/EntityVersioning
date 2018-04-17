package com.example.demo.controller;

import com.example.demo.domain.Task;
import com.example.demo.service.RevisionService;
import com.example.demo.service.TaskService;
import java.util.List;
import org.javers.core.Changes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/javers")
public class TaskRevisionController {
  private final RevisionService revisionService;
  private final TaskService taskService;

  @Autowired
  public TaskRevisionController(RevisionService revisionService,
      TaskService taskService) {
    this.revisionService = revisionService;
    this.taskService = taskService;
  }

  @GetMapping(value = "/tasks/{id}")
  public Task getTaskById(@PathVariable Long id){
    return taskService.getTaskById(id);
  }

  @GetMapping(value = "/tasks")
  public List<Task> getAllTasks(){
    return taskService.getAllTasks();
  }

  @PostMapping(value = "/tasks")
  @ResponseStatus(HttpStatus.CREATED)
  public Task createTask(@RequestBody Task task){
    return taskService.createTask(task);
  }

  @DeleteMapping(value = "/tasks/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTask(@PathVariable Long id){
    Task task = taskService.getTaskById(id);
    revisionService.commitChanges("SOME AUTHOR AHAHAOWWYEAHH", task, "comment", "udalyayu eto");
    taskService.deleteTask(id);
  }

  @GetMapping(value = "/revision/changes/{id}")
  public Changes getRevisionById(@PathVariable Long id){
    return revisionService.getTaskChangesById(id);
  }

  @GetMapping(value = "/revisions/{id}")
  public List<Task> getRevisionsById(@PathVariable Long id){
    return revisionService.getTaskPreviousStates(id);
  }
}
