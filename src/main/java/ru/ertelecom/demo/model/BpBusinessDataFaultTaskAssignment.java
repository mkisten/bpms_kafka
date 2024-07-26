package ru.ertelecom.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bp_business_data_fault_task_assignment")
@Data
public class BpBusinessDataFaultTaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;
    private LocalDateTime taskTaken;
    private String executor;
    private LocalDateTime createdAt;
    private LocalDateTime taskAssignment;
    private LocalDateTime taskEnded;
}
