package com.example.demo.repository;

import com.example.demo.domain.Task;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface TaskRepository extends JpaRepository<Task, Long>{

}
