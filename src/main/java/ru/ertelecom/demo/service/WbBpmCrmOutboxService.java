package ru.ertelecom.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.ertelecom.demo.model.WbBpmCrmOutbox;
import ru.ertelecom.demo.model.WbBpmCrmTask;
import ru.ertelecom.demo.repository.WbBpmCrmOutboxRepository;
import ru.ertelecom.demo.repository.WbBpmCrmTaskRepository;

import java.util.List;

@Service
public class WbBpmCrmOutboxService {

    private final WbBpmCrmTaskRepository taskRepository;
    private final WbBpmCrmOutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public WbBpmCrmOutboxService(WbBpmCrmTaskRepository taskRepository,
                                 WbBpmCrmOutboxRepository outboxRepository,
                                 ObjectMapper objectMapper) {
        this.taskRepository = taskRepository;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Transactional
    public void processTasks() {
        List<WbBpmCrmTask> tasks = taskRepository.findByWbctStatus("DET");
        for (WbBpmCrmTask task : tasks) {
            if (!outboxRepository.existsByWbctCode(task.getWbctCode())) {
                String jsonMessage = convertTaskToJson(task);
                WbBpmCrmOutbox outboxEntry = new WbBpmCrmOutbox();
                outboxEntry.setWbctCode(task.getWbctCode());
                outboxEntry.setMessage(jsonMessage);
                outboxEntry.setStatus("NEW");
                outboxRepository.save(outboxEntry);
            }
        }
    }

    @Transactional
    public void markMessagesAsSent() {
        List<WbBpmCrmOutbox> outboxEntries = outboxRepository.findByStatus("NEW");
        for (WbBpmCrmOutbox outboxEntry : outboxEntries) {
            outboxEntry.setStatus("SEND");
            outboxRepository.save(outboxEntry);
        }
    }

    public List<WbBpmCrmOutbox> getNewOutboxMessages() {
        return outboxRepository.findByStatus("NEW");
    }

    public String convertTaskToJson(WbBpmCrmTask task) {
        try {
            return objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка при преобразовании задачи в JSON", e);
        }
    }
}
