package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
public class DemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

    TestEnvers testEnvers = context.getBean(TestEnvers.class);

    testEnvers.letsTestIt();
    System.out.println("--------------REVISION----------------");
    testEnvers.getRevs(1);
    testEnvers.getRevs(2);
    testEnvers.getRevs(3);
    testEnvers.getRevs(4);
    testEnvers.getRevs(5);
  }
}

@Component
class TestEnvers {

  private TaskRepository taskRepository;
  private TaskService taskService;

  @Autowired
  public TestEnvers(TaskRepository taskRepository, TaskService taskService) {
    this.taskRepository = taskRepository;
    this.taskService = taskService;
  }

  public TestEnvers() {
  }

  void letsTestIt() {
    Task task = Task.builder()
        .title("Title")
        .value("Some value ohoho")
        .build();

    Task task1 = Task.builder()
        .title("Title1")
        .value("Some value ohoho1")
        .build();

    Task savedTask = taskRepository.save(task);
    Task savedTask1 = taskRepository.save(task1);

    savedTask.setValue("AHAHAHAOHOHOHOHOOWWWWYYEAAH");

    Task changedTask = taskRepository.save(savedTask);

    changedTask.setTitle("HERE WE HAVE ANOTHER TITLE");

    changedTask = taskRepository.save(changedTask);
    taskRepository.delete(savedTask1);
    System.out.println(changedTask);
  }

  void getRevs(int num){
    System.out.println(taskService.getChangesSinceLastRevision(num));
  }
}