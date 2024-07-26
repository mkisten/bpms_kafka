package ru.ertelecom.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ertelecom.demo.model.BpBusinessDataFaultTasks;

public interface BpBusinessDataFaultTasksRepository extends JpaRepository<BpBusinessDataFaultTasks, Long> {
}