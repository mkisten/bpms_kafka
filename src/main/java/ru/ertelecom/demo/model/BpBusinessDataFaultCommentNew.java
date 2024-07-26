package ru.ertelecom.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bp_business_data_fault_comment_new")
@Data
public class BpBusinessDataFaultCommentNew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long wbctCode;
    private LocalDateTime timeStamp;
    private String initiator;
    private String commentText;
}
