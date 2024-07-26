package ru.ertelecom.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ertelecom.demo.model.BpBusinessDataFaultHistory;

public interface BpBusinessDataFaultHistoryRepository extends JpaRepository<BpBusinessDataFaultHistory, Long> {
}