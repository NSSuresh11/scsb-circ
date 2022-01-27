package org.recap.consumer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.recap.BaseTestCaseUT;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * Created by sudhishk on 12/1/17.
 */
@Slf4j
public class TopicConsumerUT extends BaseTestCaseUT {



    private final String topicName = "PUL.RequestT";
    private final String initialContextFactory = "test.activemq";
    private final String connectionString = "test://localhost:61616";

    private boolean messageReceived = false;

    public static void main(String[] args) throws NamingException {
        TopicConsumerUT subscriber = new TopicConsumerUT();
        subscriber.subscribeWithTopicLookup();
    }

    public void subscribeWithTopicLookup() throws NamingException {

        Properties properties = new Properties();

        TopicConnection topicConnection = null;
        properties.put("java.naming.factory.initial", initialContextFactory);
        properties.put("connectionfactory.TopicConnectionFactory", connectionString);
        properties.put("topic." + topicName, topicName);
        try {
            InitialContext ctx = new InitialContext(properties);
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
            topicConnection = topicConnectionFactory.createTopicConnection();
            System.out.println("Create Topic Connection for Topic " + topicName);

            while (!messageReceived) {
                try {
                    TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
                    Topic topic = (Topic) ctx.lookup(topicName);
                    // start the connection
                    topicConnection.start();
                    // create a topic subscriber
                    javax.jms.TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);
                    TestMessageListener messageListener = new TestMessageListener();
                    topicSubscriber.setMessageListener(messageListener);
                    Thread.sleep(5000);
                    topicSubscriber.close();
                    topicSession.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                } catch (NamingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (NamingException e) {
            throw new RuntimeException("Error in initial context lookup", e);
        } catch (JMSException e) {
            throw new RuntimeException("Error in JMS operations", e);
        } finally {
            if (topicConnection != null) {
                try {
                    topicConnection.close();
                } catch (JMSException e) {
                    throw new RuntimeException(
                            "Error in closing topic connection", e);
                }
            }
        }
    }

    @Test
    public void ztestStringEncoding() {
        String name = "procès Laval | gri︠a︡dushchee : poluvekovai︠a︡ paradigma poėtiki Serebri︠a︡nogo Kikhneĭ, I. Erokhinoĭ]. Mikhaĭlovskoe čeká kat. Vilém  Soi︠u︡za preobrazovanni︠k︡h";
        String encoded = "";
        log.info(name);
        encoded = new String(name.getBytes(), StandardCharsets.ISO_8859_1);
        String resultString = name.replaceAll("[^\\x00-\\x7F]", "?");
        log.info(encoded);
        log.info(resultString);
        assertNotNull(name);
    }

    public class TestMessageListener implements MessageListener {
        public void onMessage(Message message) {
            try {
                System.out.println("Got the Message : " + ((TextMessage) message).getText());
                messageReceived = true;
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
