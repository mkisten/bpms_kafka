package ru.ertelecom.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bp_fault_brigades_matrix")
@Data
public class BpFaultBrigadesMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String incidentClass;
    private Boolean b2bPresent;
    private Boolean b2cPresent;
    private String object;
}
