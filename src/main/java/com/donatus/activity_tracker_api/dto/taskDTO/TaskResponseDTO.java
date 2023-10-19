package com.donatus.activity_tracker_api.dto.taskDTO;

import com.donatus.activity_tracker_api.enums.TaskStatus;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {

    private Long taskId;
    private Long clientId;
    private String activityTitle;
    private String activityDetail;
    private TaskStatus status;
    private Timestamp dueDate;
    private Instant createdDate;
    private Instant lastUpdate;
    private Timestamp closeDate;
}
