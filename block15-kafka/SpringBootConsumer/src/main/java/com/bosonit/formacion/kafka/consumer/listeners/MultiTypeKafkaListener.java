package com.bosonit.formacion.kafka.consumer.listeners;

import com.bosonit.formacion.kafka.consumer.domain.Department;
import com.bosonit.formacion.kafka.consumer.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(groupId = "multiGroup", topics = "block15-topic-multitype")
public class MultiTypeKafkaListener{

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaHandler
    public void handleEmployee(Employee employee){
        LOGGER.info("Empleado recibido: "+employee);
    }

    @KafkaHandler
    public void handleDepartment(Department department){
        LOGGER.info("Departamento recibido: "+department);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object){
        LOGGER.info("Tipo desconocido recibido: "+object);
    }
}
