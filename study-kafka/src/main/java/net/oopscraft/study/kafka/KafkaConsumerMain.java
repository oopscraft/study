package net.oopscraft.study.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerMain {
	
	private final static String TOPIC_NAME = "test2";
	private final static String GROUP_ID = "test-consumer-group";
	
	public static void main(String[] args) throws Exception {
		
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		//properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		
		
		KafkaConsumer<String,String> consumer = null;
		try {
			consumer = new KafkaConsumer<String,String>(properties);
			consumer.subscribe(Collections.singletonList(TOPIC_NAME));
			
			while(true) {
				StringBuffer buffer = new StringBuffer();
				System.out.println("=== wait...===");
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(Long.MAX_VALUE)); 
				System.out.println("=== start===");
				for (ConsumerRecord<String, String> record : records) {
					buffer.append(record.value());
	            }
				System.out.println(String.format("+ message[%s]", buffer.toString()));
			}

		}finally {
			if(consumer != null) {
				consumer.close();
			}
		}


	}

}
