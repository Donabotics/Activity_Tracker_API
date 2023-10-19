package com.donatus.activity_tracker_api.services;

import com.donatus.activity_tracker_api.dto.taskDTO.TaskRequestDTO;
import com.donatus.activity_tracker_api.dto.taskDTO.TaskResponseDTO;
import com.donatus.activity_tracker_api.entity.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskServices {
    TaskResponseDTO saveTask(Long clientId, TaskRequestDTO task);
    void removeTask(Long clientId, Long taskId);
    List<TaskResponseDTO> viewAllTasks(Long clientId);
    TaskResponseDTO viewSingleTask(Long clientId, Long taskId);
    TaskResponseDTO editTask(Long clientId, Long taskId, TaskRequestDTO task);
    List<TaskResponseDTO> filterTasksByStatus(Long clientId, String status);
    List<TaskResponseDTO> filterTasksByDate(Long clientId, String filter);
}
