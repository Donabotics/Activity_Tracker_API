package com.donatus.activity_tracker_api.utils.mappers;

import com.donatus.activity_tracker_api.dto.taskDTO.TaskRequestDTO;
import com.donatus.activity_tracker_api.dto.taskDTO.TaskResponseDTO;
import com.donatus.activity_tracker_api.entity.TaskEntity;
import com.donatus.activity_tracker_api.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskDTOMapper implements TaskDTOMapperServices{

    @Override
    public TaskResponseDTO responseMapper(TaskEntity task) {
        return TaskResponseDTO.builder()
                .taskId(task.getTaskId())
                .clientId(task.getClientEntity().getClientId())
                .activityTitle(task.getActivityTitle())
                .activityDetail(task.getActivityDetail())
                .createdDate(task.getCreatedDate())
                .lastUpdate(task.getLastUpdate())
                .dueDate(task.getDueDate())
                .closeDate(task.getCloseDate())
                .status(task.getStatus())
                .build();
    }

    @Override
    public TaskEntity requestMapper(TaskRequestDTO taskRequestDTO) {
        return TaskEntity.builder()
                .activityTitle(taskRequestDTO.getActivityTitle())
                .activityDetail(taskRequestDTO.getActivityDetail())
                .dueDate(taskRequestDTO.getDueDate())
                .status(TaskStatus.ACTIVE)
                .build();
    }

    @Override
    public TaskEntity requestMapper(TaskRequestDTO taskRequestDTO, TaskEntity task) {
        task.setActivityTitle(taskRequestDTO.getActivityTitle());
        task.setActivityDetail(taskRequestDTO.getActivityDetail());
        task.setDueDate(taskRequestDTO.getDueDate());

        return task;
    }
}
