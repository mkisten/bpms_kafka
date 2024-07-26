package ru.ertelecom.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ertelecom.demo.model.BpBusinessDataFaultTaskAssignment;

public interface BpBusinessDataFaultTaskAssignmentRepository extends JpaRepository<BpBusinessDataFaultTaskAssignment, Long> {
}