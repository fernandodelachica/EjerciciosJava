package com.bosonit.formacion.kafka.consumer.listeners;

import com.bosonit.formacion.kafka.consumer.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);


    @KafkaListener(topics = {"block15-topic"} , groupId = "group-one", containerFactory = "stringConsumerKafkaListenerContainerFactory")
    public void listener(String message){
        LOGGER.info("Mensaje recibido, el mensaje es: "+message);
    }

    @KafkaListener(
            topics = {"block15-topic-employee"},
            groupId = "employee",
            containerFactory = "employeeConcurrentKafkaListenerContainerFactory"
    )
    public void employeeListener(Employee employee){
        LOGGER.info("Mensaje recibido, el mensaje es: "+employee);
    }

}
