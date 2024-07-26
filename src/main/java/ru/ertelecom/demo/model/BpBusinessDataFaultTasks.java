package ru.ertelecom.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bp_business_data_fault_tasks")
@Data
public class BpBusinessDataFaultTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String executor;
    private String taskPlanned;
    private LocalDateTime taskPlannedDate;
    private LocalDateTime taskEndDate;
    private Long parentTaskId;
    private String taskAssignmentId;
}
