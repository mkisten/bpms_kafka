package ru.ertelecom.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.ertelecom.demo.model.WbBpmCrmOutbox;

import java.util.List;

@Service
public class BpmsKafkaProducer {

    private final WbBpmCrmOutboxService outboxService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic.dev}")
    public String devTopic;

    @Value("${kafka.topic.prod}")
    public String prodTopic;

    @Autowired
    public BpmsKafkaProducer(WbBpmCrmOutboxService outboxService,
                             KafkaTemplate<String, String> kafkaTemplate) {
        this.outboxService = outboxService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 60000)
    public void processTasks() {
        outboxService.processTasks();
    }

    @Scheduled(fixedRate = 60000)
    public void sendOutboxMessages() {
        List<WbBpmCrmOutbox> outboxEntries = outboxService.getNewOutboxMessages();
        for (WbBpmCrmOutbox outboxEntry : outboxEntries) {
            // Отправка в dev топик
            kafkaTemplate.send(devTopic, outboxEntry.getMessage());
            // Отправка в prod топик
            kafkaTemplate.send(prodTopic, outboxEntry.getMessage());
            // Пометка сообщений как отправленные
            outboxService.markMessagesAsSent();
        }
    }
}