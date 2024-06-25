package ru.ertelecom.demo.sevice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import ru.ertelecom.demo.model.WbBpmCrmOutbox;
import ru.ertelecom.demo.service.BpmsKafkaProducer;
import ru.ertelecom.demo.service.WbBpmCrmOutboxService;

import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

public class BpmsKafkaProducerTest {

    @Mock
    private WbBpmCrmOutboxService outboxService;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private BpmsKafkaProducer producer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producer = new BpmsKafkaProducer(outboxService, kafkaTemplate);
        producer.devTopic = "dev-topic";
        producer.prodTopic = "prod-topic";
    }

    @Test
    void testProcessTasks() {
        producer.processTasks();
        verify(outboxService, times(1)).processTasks();
    }

    @Test
    void testSendOutboxMessages() {
        WbBpmCrmOutbox outboxEntry = new WbBpmCrmOutbox();
        outboxEntry.setMessage("test-message");
        List<WbBpmCrmOutbox> outboxEntries = Collections.singletonList(outboxEntry);

        when(outboxService.getNewOutboxMessages()).thenReturn(outboxEntries);

        producer.sendOutboxMessages();

        verify(kafkaTemplate, times(1)).send("dev-topic", "test-message");
        verify(kafkaTemplate, times(1)).send("prod-topic", "test-message");
        verify(outboxService, times(1)).markMessagesAsSent();
    }
}
