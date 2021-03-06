package kafkaproducers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class EmployeeSenderWithCustomPartitioner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Properties props=new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmployeeSerializer.class.getName());
		props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, EmployeePartitioner.class.getName());
		
		KafkaProducer<String, Employee> producer=new KafkaProducer<>(props);
		String topic="emp-topic-custom";
		for(int i=1001;i<=1010;i++) {
			ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
					 new Employee(i, "Name "+i, "Developer"));
			producer.send(record);
		}
		for(int i=2001;i<=2010;i++) {
			ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
					"test-key", new Employee(i, "Name "+i, "Accountant"));
			producer.send(record);
		}
		for(int i=3001;i<=3010;i++) {
			ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
					"test-key", new Employee(i, "Name "+i, "Architect"));
			producer.send(record);
		}
		for(int i=4001;i<=4010;i++) {
			ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
					"test-key", new Employee(i, "Name "+i, "IT_Admin"));
			producer.send(record);
		}
		for(int i=5001;i<=5010;i++) {
			ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
					"test-key", new Employee(i, "Name "+i, "DBA"));
			producer.send(record);
		}
		System.out.println("messages sent");
		producer.close();
	}

}