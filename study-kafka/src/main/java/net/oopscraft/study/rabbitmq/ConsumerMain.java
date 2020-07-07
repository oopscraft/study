package net.oopscraft.study.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class ConsumerMain {
	
	private final static String QUEUE_NAME = "test";
	
	public static void main(String[] args) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        Connection connection = null;
        Channel channel = null;
        try {
        	connection = factory.newConnection(); 
        	channel = connection.createChannel();
        	channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        	channel.basicConsume(QUEUE_NAME, true, new Consumer() {
				public void handleConsumeOk(String consumerTag) {
					System.out.println(String.format("[%s]Consumer.handleConsumeOk", consumerTag));
				}
				public void handleCancelOk(String consumerTag) {
					System.out.println(String.format("[%s]Consumer.handleCancelOk", consumerTag));
				}
				public void handleCancel(String consumerTag) throws IOException {
					System.out.println(String.format("[%s]Consumer.handleCancel", consumerTag));
				}
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] bytes) throws IOException {
					System.out.println(String.format("[%s]Consumer.handleDelivery", consumerTag));
					System.out.println("envelope:" + envelope);
					System.out.println("properties:" + properties);
					System.out.println("bytes:" + bytes);
				}
				public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
					System.out.println(String.format("[%s]Consumer.handleShutdownSignal", consumerTag));
				}
				public void handleRecoverOk(String consumerTag) {
					System.out.println(String.format("[%s]Consumer.handleRecoverOk", consumerTag));
				}
        	});
        	
        	// thread join
        	Thread.currentThread().join();
        	
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if(channel != null) try { channel.close(); }catch(Exception ignore) {}
        	if(connection != null) try { connection.close(); }catch(Exception ignore) {}
        }
	}

}
