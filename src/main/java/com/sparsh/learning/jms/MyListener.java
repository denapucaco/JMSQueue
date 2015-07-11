/**
 * 
 */
package com.sparsh.learning.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author prashant.swamy
 *
 */
public class MyListener implements MessageListener {

    /*
     * (non-Javadoc)
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void onMessage(Message message) {

        try {
            if (message instanceof TextMessage) {
                String textMessage = ((TextMessage) message).getText();
                System.out.println("Message Received :: " + textMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
