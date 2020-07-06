package net.oopscraft.study.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqTest {
	
	private static final String QUEUE_NAME = "test";
	
	
	public static void main(String[] args) throws Exception {

	    ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
//        factory.setUsername("uguest");
//        factory.setPassword("pguest");
        Connection connection = null;
        Channel channel = null;
        
        try {
        	connection = factory.newConnection(); 
        	channel = connection.createChannel();
            for (int i = 0; i <= 100000; i++) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Hello World!" + (int) (Math.random() * 100);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Set '" + message + "'");
                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if(channel != null) try { channel.close(); }catch(Exception ignore) {}
        	if(connection != null) try { connection.close(); }catch(Exception ignore) {}
        }
		
	}
	
	


}
