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
    public String bootstrapServers;

    @Value("${kafka.topic.dev}")
    public String devTopic;

    @Value("${kafka.topic.prod}")
    public String prodTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicDev() {
        return createTopic(devTopic);
    }

    @Bean
    public NewTopic topicProd() {
        return createTopic(prodTopic);
    }

    private NewTopic createTopic(String name) {
        return new NewTopic(name, 3, (short) 1);
    }
}
