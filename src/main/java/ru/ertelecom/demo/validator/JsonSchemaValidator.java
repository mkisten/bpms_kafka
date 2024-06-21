package ru.ertelecom.demo.validator;

import jakarta.annotation.PostConstruct;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class JsonSchemaValidator {

    private Schema schema;

    @Value("classpath:schemas/wb_bpm_crm_task_schema.json")
    private Resource schemaResource;

    @PostConstruct
    public void init() throws IOException {
        try (InputStream inputStream = schemaResource.getInputStream()) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            this.schema = SchemaLoader.load(rawSchema);
        }
    }

    public void validate(JSONObject jsonObject) {
        schema.validate(jsonObject);
    }
}
