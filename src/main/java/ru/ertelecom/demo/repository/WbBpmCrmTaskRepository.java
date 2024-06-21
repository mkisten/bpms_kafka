package ru.ertelecom.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ertelecom.demo.model.WbBpmCrmTask;

import java.util.List;

@Repository
public interface WbBpmCrmTaskRepository extends JpaRepository<WbBpmCrmTask, Long> {
    List<WbBpmCrmTask> findByWbctStatus(String wbctStatus);
}
