package ru.ertelecom.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicDev() {
        return createTopic("wb_bpm_crm_tasks_dev");
    }

    @Bean
    public NewTopic topicProd() {
        return createTopic("wb_bpm_crm_tasks_prod");
    }
    @Bean
    public NewTopic topicStatus() {
        return createTopic("wb_bpm_crm_tasks_status");
    }
    private NewTopic createTopic(String name) {
        return new NewTopic(name, 3, (short) 1);
    }
}
