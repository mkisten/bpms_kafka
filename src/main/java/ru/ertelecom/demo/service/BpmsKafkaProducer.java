package ru.ertelecom.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.ertelecom.demo.model.WbBpmCrmTask;
import ru.ertelecom.demo.repository.WbBpmCrmTaskRepository;
import ru.ertelecom.demo.validator.JsonSchemaValidator;

import java.util.List;

@Service
public class BpmsKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(BpmsKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private JsonSchemaValidator jsonSchemaValidator;

    @Autowired
    private WbBpmCrmTaskRepository wbBpmCrmTaskRepository;

    @Value("${kafka.topic.dev}")
    public String devTopic;

    @Value("${kafka.topic.prod}")
    public String prodTopic;

    @Value("${kafka.topic.status}")
    public String statusTopic;

    private final ObjectMapper objectMapper;

    @Autowired
    public BpmsKafkaProducer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void sendMessages() {
        try {
            List<WbBpmCrmTask> tasks = wbBpmCrmTaskRepository.findByWbctStatus("DET");
            for (WbBpmCrmTask task : tasks) {
                String jsonMessage = objectMapper.writeValueAsString(task);
                JSONObject jsonObject = new JSONObject(jsonMessage);
                try {
                    jsonSchemaValidator.validate(jsonObject);  // Валидация данных
                    kafkaTemplate.send(devTopic, jsonMessage);
                    kafkaTemplate.send(prodTopic, jsonMessage);
                    logger.info("Message sent: {}", jsonMessage);
                    kafkaTemplate.send(statusTopic, "отправлено");
                } catch (Exception validationException) {
                    logger.error("Validation failed for message: {}", jsonMessage, validationException);
                    kafkaTemplate.send(statusTopic, "не отправлено");
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred while sending messages", e);
        }
    }
}
