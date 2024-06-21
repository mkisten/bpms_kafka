package ru.ertelecom.demo.config;

import ru.ertelecom.demo.service.BpmsKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private BpmsKafkaProducer bpmsKafkaProducer;

    @Scheduled(fixedRate = 60000)  // Выполнять каждые 60 секунд
    public void scheduleTask() {
        try {
            bpmsKafkaProducer.sendMessages();
        } catch (Exception e) {
            // Логирование вместо printStackTrace
            // log.error("Error occurred while sending messages", e);
        }
    }
}
