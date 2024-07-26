package ru.ertelecom.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ertelecom.demo.model.WbBpmCrmOutbox;
import ru.ertelecom.demo.repository.WbBpmCrmOutboxRepository;

import java.util.List;

@Service
public class WbBpmCrmOutboxService {
    private static final Logger logger = LoggerFactory.getLogger(WbBpmCrmOutboxService.class);

    private final WbBpmCrmOutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public WbBpmCrmOutboxService(WbBpmCrmOutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void processTasks(List<?> tasks) {
        logger.info("Запуск метода processTasks");

        for (Object task : tasks) {
            try {
                String jsonMessage = objectMapper.writeValueAsString(task);
                WbBpmCrmOutbox outboxEntry = new WbBpmCrmOutbox();
                outboxEntry.setMessage(jsonMessage);
                outboxEntry.setStatus("NEW");
                outboxRepository.save(outboxEntry);
            } catch (JsonProcessingException e) {
                logger.error("Ошибка при преобразовании задачи в JSON", e);
            }
        }

        logger.info("Завершение метода processTasks");
    }

    @Transactional
    public void markMessagesAsSent() {
        logger.info("Запуск метода markMessagesAsSent");

        List<WbBpmCrmOutbox> outboxEntries = outboxRepository.findByStatus("NEW");

        for (WbBpmCrmOutbox outboxEntry : outboxEntries) {
            outboxEntry.setStatus("SEND");
            outboxRepository.save(outboxEntry);
        }

        logger.info("Завершение метода markMessagesAsSent");
    }

    public List<WbBpmCrmOutbox> getNewOutboxMessages() {
        logger.info("Получение новых сообщений в Outbox");
        return outboxRepository.findByStatus("NEW");
    }
}
