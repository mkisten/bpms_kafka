package ru.ertelecom.demo.sevice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ertelecom.demo.model.WbBpmCrmOutbox;
import ru.ertelecom.demo.model.WbBpmCrmTask;
import ru.ertelecom.demo.repository.WbBpmCrmOutboxRepository;
import ru.ertelecom.demo.repository.WbBpmCrmTaskRepository;
import ru.ertelecom.demo.service.WbBpmCrmOutboxService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WbBpmCrmOutboxServiceTest {

    @Mock
    private WbBpmCrmTaskRepository taskRepository;

    @Mock
    private WbBpmCrmOutboxRepository outboxRepository;

    @Mock
    private ObjectMapper objectMapper;

    private WbBpmCrmOutboxService service;
    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        service = new WbBpmCrmOutboxService(taskRepository, outboxRepository, objectMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void testProcessTasks() throws JsonProcessingException {
        WbBpmCrmTask task = new WbBpmCrmTask();
        task.setWbctCode(123L); // Используем Long значение вместо строки
        List<WbBpmCrmTask> tasks = Collections.singletonList(task);

        when(taskRepository.findByWbctStatus("DET")).thenReturn(tasks);
        when(outboxRepository.existsByWbctCode(123L)).thenReturn(false); // Используем Long значение
        when(objectMapper.writeValueAsString(task)).thenReturn("json-message");

        service.processTasks();

        verify(outboxRepository, times(1)).save(any(WbBpmCrmOutbox.class));
    }

    @Test
    void testMarkMessagesAsSent() {
        WbBpmCrmOutbox outboxEntry = new WbBpmCrmOutbox();
        outboxEntry.setStatus("NEW");
        List<WbBpmCrmOutbox> outboxEntries = Collections.singletonList(outboxEntry);

        when(outboxRepository.findByStatus("NEW")).thenReturn(outboxEntries);

        service.markMessagesAsSent();

        assertEquals("SEND", outboxEntry.getStatus());
        verify(outboxRepository, times(1)).save(outboxEntry);
    }

    @Test
    void testGetNewOutboxMessages() {
        List<WbBpmCrmOutbox> outboxEntries = Collections.singletonList(new WbBpmCrmOutbox());

        when(outboxRepository.findByStatus("NEW")).thenReturn(outboxEntries);

        List<WbBpmCrmOutbox> result = service.getNewOutboxMessages();

        assertEquals(outboxEntries, result);
    }

    @Test
    void testConvertTaskToJson() throws JsonProcessingException {
        WbBpmCrmTask task = new WbBpmCrmTask();
        when(objectMapper.writeValueAsString(task)).thenReturn("json-message");

        String result = service.convertTaskToJson(task);

        assertEquals("json-message", result);
    }

    @Test
    void testConvertTaskToJson_Exception() throws JsonProcessingException {
        WbBpmCrmTask task = new WbBpmCrmTask();
        when(objectMapper.writeValueAsString(task)).thenThrow(new JsonProcessingException("error"){});

        assertThrows(RuntimeException.class, () -> service.convertTaskToJson(task));
    }
}
