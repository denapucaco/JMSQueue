/**
 * 
 */
package com.sparsh.learning.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author prashant.swamy
 *
 */
public class Consumer implements Runnable {

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {

        Connection connection = null;

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("messageQueue");

            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                String textMessage = ((TextMessage) message).getText();
                System.out.println("Received :: " + textMessage);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
