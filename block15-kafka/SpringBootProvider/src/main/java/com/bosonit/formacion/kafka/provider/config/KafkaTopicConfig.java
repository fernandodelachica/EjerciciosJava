package com.bosonit.formacion.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){
        Map<String, String > configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); //DELETE Borra mensaje, COMPACT Mantiene el más actual
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");    //Tiempo de retención de mensajes - Default: -1 = No se borrarán nunca
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Tamaño máximo del segmento - Default: 1GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");  //Tamaño máximo de cada mensaje - Default: 1 MB


        return TopicBuilder.name("block15-topic")
                .partitions(2)
                .replicas(2)        //Copias del topic, un respaldo del topic
                .configs(configurations)
                .build();
    }

    @Bean
    public NewTopic generateEmployeeTopic(){
        Map<String, String > configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); //DELETE Borra mensaje, COMPACT Mantiene el más actual
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");    //Tiempo de retención de mensajes - Default: -1 = No se borrarán nunca
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Tamaño máximo del segmento - Default: 1GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");  //Tamaño máximo de cada mensaje - Default: 1 MB


        return TopicBuilder.name("block15-topic-employee")
                .partitions(2)
                .replicas(2)        //Copias del topic, un respaldo del topic
                .configs(configurations)
                .build();
    }

    @Bean
    public NewTopic generateMultiTypeTopic(){
        Map<String, String > configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); //DELETE Borra mensaje, COMPACT Mantiene el más actual
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");    //Tiempo de retención de mensajes - Default: -1 = No se borrarán nunca
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Tamaño máximo del segmento - Default: 1GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");  //Tamaño máximo de cada mensaje - Default: 1 MB


        return TopicBuilder.name("block15topic-multitype")
                .partitions(2)
                .replicas(2)        //Copias del topic, un respaldo del topic
                .configs(configurations)
                .build();
    }
}
