package com.donatus.activity_tracker_api.dto.taskDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String activityTitle;

    @Size(min = 5, max = 1000)
    private String activityDetail;

    @NotBlank
    private Timestamp dueDate;
}
