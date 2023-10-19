package com.donatus.activity_tracker_api.services.implimentation;

import com.donatus.activity_tracker_api.dto.taskDTO.TaskRequestDTO;
import com.donatus.activity_tracker_api.dto.taskDTO.TaskResponseDTO;
import com.donatus.activity_tracker_api.entity.ClientEntity;
import com.donatus.activity_tracker_api.entity.TaskEntity;
import com.donatus.activity_tracker_api.enums.TaskStatus;
import com.donatus.activity_tracker_api.exception.TaskNotFoundException;
import com.donatus.activity_tracker_api.repository.TaskRepository;
import com.donatus.activity_tracker_api.services.TaskServices;
import com.donatus.activity_tracker_api.utils.ClientCheck;
import com.donatus.activity_tracker_api.utils.mappers.TaskDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServicesImpl implements TaskServices {

    private final TaskRepository taskRepo;
    private final ClientCheck check;
    private final TaskDTOMapper taskDTOMapper;


    @Override
    public TaskResponseDTO saveTask(Long clientId, TaskRequestDTO newTask) {
        Optional<ClientEntity> client = check.checkClient(clientId);

        TaskEntity mappedClient = taskDTOMapper.requestMapper(newTask);
        client.get().addTask(mappedClient);

        return taskDTOMapper.responseMapper(taskRepo.save(mappedClient));
    }

    @Transactional
    @Override
    public void removeTask(Long clientId, Long taskId) {
        taskRepo.removeTaskEntityByClientEntityClientIdAndTaskId(clientId, taskId);
    }

    @Override
    public List<TaskResponseDTO> viewAllTasks(Long clientId) {
        List<TaskEntity> task = taskRepo.findTaskEntityByClientEntityClientId(clientId);

        return task.stream()
                .map(taskDTOMapper::responseMapper)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO viewSingleTask(Long clientId, Long taskId) {
        Optional<TaskEntity> result = taskRepo.findTaskEntityByClientEntityClientIdAndTaskId(clientId, taskId);

        if (result.isEmpty()){
            throw new TaskNotFoundException("Task Not Found!");
        }

        return taskDTOMapper.responseMapper(result.get());
    }

    @Override
    public TaskResponseDTO editTask(Long clientId, Long taskId, TaskRequestDTO task) {

        Optional<TaskEntity> taskEntity = taskRepo.findTaskEntityByClientEntityClientIdAndTaskId(clientId, taskId);
        if (taskEntity.isEmpty()){
            throw new TaskNotFoundException("Task Not Found!");
        }

        taskEntity = Optional.ofNullable(taskDTOMapper.requestMapper(task, taskEntity.get()));
        taskEntity.get().setTaskId(taskId);

        Optional<ClientEntity> client = check.checkClient(clientId);

        client.get().addTask(taskEntity.get());

        return taskDTOMapper.responseMapper(taskRepo.save(taskEntity.get()));
    }

    @Override
    public List<TaskResponseDTO> filterTasksByStatus(Long clientId, String status) {
        List<TaskEntity> taskEntities = taskRepo.
                findTaskEntityByClientEntityClientIdAndStatus(clientId, TaskStatus.valueOf(status.toUpperCase()));

        return taskEntities.stream()
                .map(taskDTOMapper::responseMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDTO> filterTasksByDate(Long clientId, String filter) {
        List<TaskEntity> byDueDate = taskRepo.findTaskEntityByClientEntityClientIdOrderByDueDate(clientId);
        List<TaskEntity> byCreatedDate = taskRepo.findTaskEntityByClientEntityClientIdOrderByCreatedDate(clientId);

        return "DUE_DATE".equalsIgnoreCase(filter) ? byDueDate.stream().map(taskDTOMapper::responseMapper).toList() :
                "CREATION_DATE".equalsIgnoreCase(filter) ? byCreatedDate.stream().map(taskDTOMapper::responseMapper).toList() :
                new ArrayList<>();
    }
}
