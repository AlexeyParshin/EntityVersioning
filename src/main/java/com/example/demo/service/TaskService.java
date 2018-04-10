package com.example.demo.service;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

  private EntityManager entityManager;

  @Autowired
  public TaskService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  public Map<RevisionType, List<Object>> getChangesSinceLastRevision(int number) {
    AuditReader auditReader = AuditReaderFactory.get(entityManager);
    Map<RevisionType, List<Object>> entitiesGroupByRevisionType = auditReader
        .getCrossTypeRevisionChangesReader()
        .findEntitiesGroupByRevisionType(number);

    return entitiesGroupByRevisionType;
  }
}
