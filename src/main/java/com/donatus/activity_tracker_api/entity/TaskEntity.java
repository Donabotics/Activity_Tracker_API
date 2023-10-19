package com.donatus.activity_tracker_api.entity;

import com.donatus.activity_tracker_api.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "client_tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(min = 3, max = 100)
    private String activityTitle;

    @Column(length = 1000)
    @Size(min = 5, max = 1000)
    private String activityDetail;
    
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
    private Timestamp dueDate;
    
    @CreationTimestamp
    private Instant createdDate;
    
    @UpdateTimestamp
    private Instant lastUpdate;
    
    private Timestamp closeDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
                                                 CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;
}
