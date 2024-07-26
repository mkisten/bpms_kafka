package ru.ertelecom.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bp_business_data_fault_sap")
@Data
public class BpBusinessDataFaultSap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;
    private String toroObject;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime assignmentDate;
    private String requestId;
    private String message;
    private String sapStatusId;
    private String executor;
}
