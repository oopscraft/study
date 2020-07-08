package net.oopscraft.study.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProvider {
	
	private final static String TOPIC_NAME = "test2";
	
	public static void main(String[] args) throws Exception {

		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		KafkaProducer<String,String> producer = null;
		try {
			producer = new KafkaProducer<String,String>(properties);
			for(int i  = 0; i < 10; i ++ ) {
				ProducerRecord<String,String> record = new ProducerRecord<String,String>(TOPIC_NAME, "key" + i, "This is value " + i + System.lineSeparator());
				System.out.println("send:" + record);
				producer.send(record);
				producer.flush();
			}

		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			if(producer !=null) {
				producer.close();
			}
		}

	}


}
