package com.example.demo.service;

import static java.util.stream.Collectors.toList;

import com.example.demo.domain.Task;
import java.util.List;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevisionService {

  private final Javers javers;

  @Autowired
  public RevisionService(Javers javers) {
    this.javers = javers;
  }

  public Changes getTaskChangesById(Long id){
    Changes changes = javers
        .findChanges(QueryBuilder.byInstanceId(id, Task.class).build());

    return changes;
  }

  public List<Task> getTaskPreviousStates(Long id){
    List<Shadow<Task>> shadows = javers
        .findShadows(QueryBuilder.byInstanceId(id, Task.class).build());

    return shadows.stream()
        .map(Shadow::get)
        .collect(toList());
  }
}
