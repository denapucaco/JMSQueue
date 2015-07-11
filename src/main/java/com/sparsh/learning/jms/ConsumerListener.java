/**
 * 
 */
package com.sparsh.learning.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author prashant.swamy
 *
 */
public class ConsumerListener {

    public static void main(String... strings) {
        Connection connection = null;
        Session session = null;

        try {
            //
            // new Thread(new Producer()).start();
            // new Thread(new Producer()).start();
            //
            // new Thread(new Producer()).start();
            // new Thread(new Producer()).start();
            //
            // Thread.sleep(1000);
            // new Thread(new Producer()).start();
            // new Thread(new Producer()).start();
            // new Thread(new Producer()).start();
            //
            // Thread.sleep(2000);
            // new Thread(new Producer()).start();
            // new Thread(new Producer()).start();
            //
            // Thread.sleep(3000);
            // new Thread(new Producer()).start();
            // new Thread(new Producer()).start();
            // new Thread(new Producer()).start();

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = connectionFactory.createConnection();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("messageQueue");

            MyListener listener = new MyListener();

            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(listener);

            connection.start();

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // DO not close the connection
            // Close of connection automatically closes the session.
        }
    }
}
