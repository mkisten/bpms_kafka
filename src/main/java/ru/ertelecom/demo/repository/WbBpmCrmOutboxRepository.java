package ru.ertelecom.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ertelecom.demo.model.WbBpmCrmOutbox;

import java.util.List;

public interface WbBpmCrmOutboxRepository extends JpaRepository<WbBpmCrmOutbox, Long> {
    List<WbBpmCrmOutbox> findByStatus(String status);
    boolean existsByWbctCode(Long wbctCode);
}