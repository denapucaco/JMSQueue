/**
 * 
 */
package com.sparsh.learning.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author prashant.swamy
 *
 */
public class Producer implements Runnable {

    public void run() {

        Connection connection = null;
        Session session = null;

        try {
            // InitialContext jndi = new InitialContext();
            // ConnectionFactory connectionFactory = (ConnectionFactory) jndi.lookup("java:/ConnectionFactory");

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Destination destination = (Destination) jndi.lookup("java:/jms/queue/messageQueue");

            Destination destination = session.createQueue("messageQueue");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String text = "Hello World from :: " + Thread.currentThread().getName() + " :: " + this.hashCode();
            TextMessage textMessage = session.createTextMessage(text);

            producer.send(textMessage);
            System.out.println("Sending msg: " + textMessage.getText());

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {

            try {
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }

}
