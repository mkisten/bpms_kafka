package ru.ertelecom.demo.validator;

import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class JsonSchemaValidatorTests {

    @InjectMocks
    private JsonSchemaValidator jsonSchemaValidator;

    @Mock
    private Resource schemaResource;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(schemaResource.getInputStream()).thenReturn(new ClassPathResource("schemas/wb_bpm_crm_task_schema.json").getInputStream());
        jsonSchemaValidator.init();
    }

    @Test
    void testValidate() {
        // Assuming this JSON is valid according to your schema
        JSONObject validJsonObject = new JSONObject()
                .put("wbctCode", 123)
                .put("wbctStatus", "active")
                .put("wbctDt", "2023-06-20T12:00:00Z")
                .put("wbctTaskData", "Task Data")
                .put("wbctTaskType", "Type1");

        assertDoesNotThrow(() -> jsonSchemaValidator.validate(validJsonObject));
    }

    @Test
    void testValidateInvalidJson() {
        // This JSON should be invalid according to your schema
        JSONObject invalidJsonObject = new JSONObject()
                .put("wbctCode", 123)
                .put("wbctStatus", "active");

        assertThrows(ValidationException.class, () -> jsonSchemaValidator.validate(invalidJsonObject));
    }
}
