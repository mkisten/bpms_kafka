package ru.ertelecom.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "wb_bpm_crm_outbox")
public class WbBpmCrmOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long wbctCode;

    @Column(name = "message", columnDefinition = "text")
    private String message;

    private String status;
}
