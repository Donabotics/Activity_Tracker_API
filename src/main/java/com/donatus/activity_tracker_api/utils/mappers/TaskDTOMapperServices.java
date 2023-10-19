package com.donatus.activity_tracker_api.utils.mappers;

import com.donatus.activity_tracker_api.dto.taskDTO.TaskRequestDTO;
import com.donatus.activity_tracker_api.dto.taskDTO.TaskResponseDTO;
import com.donatus.activity_tracker_api.entity.TaskEntity;

public interface TaskDTOMapperServices {

    TaskResponseDTO responseMapper(TaskEntity task);
    TaskEntity requestMapper(TaskRequestDTO taskRequestDTO);
    TaskEntity requestMapper(TaskRequestDTO taskRequestDTO, TaskEntity task);
}
