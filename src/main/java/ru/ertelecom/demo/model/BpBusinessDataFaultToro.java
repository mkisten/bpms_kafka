package ru.ertelecom.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bp_business_data_fault_toro")
@Data
public class BpBusinessDataFaultToro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toroObject;
    private String createdToroObject;
    private Long requestId;
    private String requestResult;
    private String message;
    private String toroStatusId;
    private String executor;
}
