package com.donatus.activity_tracker_api.rest;

import com.donatus.activity_tracker_api.dto.taskDTO.TaskRequestDTO;
import com.donatus.activity_tracker_api.dto.taskDTO.TaskResponseDTO;
import com.donatus.activity_tracker_api.services.TaskServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskServices services;

    @PostMapping("/{clientId}")
    public ResponseEntity<TaskResponseDTO> newTask(@PathVariable Long clientId, @RequestBody TaskRequestDTO task){
        return ResponseEntity.ok(services.saveTask(clientId, task));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<TaskResponseDTO>> showAllTask(@PathVariable Long clientId){
        return ResponseEntity.ok(services.viewAllTasks(clientId));
    }

    @GetMapping("/{clientId}/task_id/{taskId}")
    public ResponseEntity<TaskResponseDTO> showSingleTask(@PathVariable Long clientId, @PathVariable Long taskId){
        return ResponseEntity.ok(services.viewSingleTask(clientId, taskId));
    }

    @PutMapping("/{clientId}/task_id/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long clientId,
                                                      @PathVariable Long taskId,
                                                      @RequestBody TaskRequestDTO task){
        return ResponseEntity.ok(services.editTask(clientId, taskId, task));
    }

    @GetMapping("/{clientId}/status/{status}")
    public ResponseEntity<List<TaskResponseDTO>> showTaskByStatus(@PathVariable Long clientId, @PathVariable String status){
        return ResponseEntity.ok(services.filterTasksByStatus(clientId, status));
    }


    @GetMapping("/{clientId}/filter/{filter}")
    public ResponseEntity<List<TaskResponseDTO>> showTaskByDate(@PathVariable Long clientId, @PathVariable String filter){
        return ResponseEntity.ok(services.filterTasksByDate(clientId, filter));
    }

    @DeleteMapping("/{clientId}/task_id/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long clientId, @PathVariable Long taskId){
        services.removeTask(clientId, taskId);

        return ResponseEntity.ok("Task deleted successfully!");
    }
}
