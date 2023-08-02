package com.bosonit.formacion.kafka.provider;

import com.bosonit.formacion.kafka.provider.domain.Department;
import com.bosonit.formacion.kafka.provider.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringBootProviderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootProviderApplication.class, args);

		MessageProducer producer = context.getBean(MessageProducer.class);
		producer.sendEmployeeMessages();
		producer.sendStringMessage();
		producer.sendMultiTypeMessages();
	}

	@Bean
	public MessageProducer MessageProducer(){
		return new MessageProducer();
	}

	public static class MessageProducer{

		@Autowired
		private KafkaTemplate<String, String> stringKafkaTemplate;

		@Autowired
		private KafkaTemplate<String, Employee> employeeKafkaTemplate;

		@Autowired
		private KafkaTemplate<String, Object> multiTypeKafkaTemplate;

		public void sendMultiTypeMessages(){
			Employee employee = new Employee(2, "Julia", "Martinez", "Design", 21000, 21000/12);
			Department department = new Department("Google", "Granada", 400);

			multiTypeKafkaTemplate.send("block15-topic-multitype", employee);
			multiTypeKafkaTemplate.send("block15-topic-multitype", department);
			multiTypeKafkaTemplate.send("block15-topic-multitype", "Esto es un String");
		}

		public void sendEmployeeMessages(){
			Employee employee = new Employee(1, "Mauricio", "Perez", "Backend", 20000, 1500);

			employeeKafkaTemplate.send("block15-topic-employee", employee);
		}

		public void sendStringMessage(){
			stringKafkaTemplate.send("block15-topic", "Mensaje tipo String enviado a block15-topic");
		}
	}



}
