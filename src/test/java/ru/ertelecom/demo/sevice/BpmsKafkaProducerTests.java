package ru.ertelecom.demo.sevice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import ru.ertelecom.demo.model.WbBpmCrmTask;
import ru.ertelecom.demo.repository.WbBpmCrmTaskRepository;
import ru.ertelecom.demo.service.BpmsKafkaProducer;
import ru.ertelecom.demo.validator.JsonSchemaValidator;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BpmsKafkaProducerTests {

    @InjectMocks
    private BpmsKafkaProducer bpmsKafkaProducer;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private WbBpmCrmTaskRepository repository;

    @Mock
    private JsonSchemaValidator jsonSchemaValidator;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bpmsKafkaProducer.devTopic = "wb_bpm_crm_tasks_dev";  // Установите значение темы
        bpmsKafkaProducer.prodTopic = "wb_bpm_crm_tasks_prod";  // Установите значение темы
    }

    @Test
    public void testSendMessages() throws Exception {
        WbBpmCrmTask task = new WbBpmCrmTask();
        task.setWbctCode(1L);
        task.setWbctStatus("DET");
        task.setWbctTaskData("Task Data");

        List<WbBpmCrmTask> tasks = Collections.singletonList(task);

        when(repository.findByWbctStatus("DET")).thenReturn(tasks);
        doNothing().when(jsonSchemaValidator).validate(any(JSONObject.class));

        String jsonMessage = objectMapper.writeValueAsString(task);
        JSONObject jsonObject = new JSONObject(jsonMessage);

        bpmsKafkaProducer.sendMessages();

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<JSONObject> jsonObjectCaptor = ArgumentCaptor.forClass(JSONObject.class);

        verify(kafkaTemplate, times(2)).send(topicCaptor.capture(), messageCaptor.capture());
        List<String> capturedTopics = topicCaptor.getAllValues();
        List<String> capturedMessages = messageCaptor.getAllValues();

        assertEquals("wb_bpm_crm_tasks_dev", capturedTopics.get(0));
        assertEquals("wb_bpm_crm_tasks_prod", capturedTopics.get(1));
        assertEquals(jsonMessage, capturedMessages.get(0));
        assertEquals(jsonMessage, capturedMessages.get(1));

        verify(jsonSchemaValidator, times(1)).validate(jsonObjectCaptor.capture());
        JSONObject capturedJsonObject = jsonObjectCaptor.getValue();
        assertEquals(jsonObject.toString(), capturedJsonObject.toString());
    }
}
