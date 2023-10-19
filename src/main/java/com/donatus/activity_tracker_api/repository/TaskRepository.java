package com.donatus.activity_tracker_api.repository;

import com.donatus.activity_tracker_api.entity.TaskEntity;
import com.donatus.activity_tracker_api.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    void removeTaskEntityByClientEntityClientIdAndTaskId(Long clientId, Long taskId);
    List<TaskEntity> findTaskEntityByClientEntityClientId(Long clientId);
    Optional<TaskEntity> findTaskEntityByClientEntityClientIdAndTaskId(Long clientId, Long taskId);
    List<TaskEntity> findTaskEntityByClientEntityClientIdAndStatus(Long clientEntity_clientId, TaskStatus status);
    List<TaskEntity> findTaskEntityByClientEntityClientIdOrderByDueDate(Long clientId);
    List<TaskEntity> findTaskEntityByClientEntityClientIdOrderByCreatedDate(Long clientId);
}
