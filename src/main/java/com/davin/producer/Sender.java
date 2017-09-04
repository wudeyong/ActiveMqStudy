package com.davin.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Author :davin
 * Date 2017/9/2
 * For More Information,Please Visit https://github.com/wudeyong
 */
public class Sender {

    public static void sendMessage(String message){
        //工厂
        ConnectionFactory connectionFactory;
        //connection
        Connection connection;
        String url="tcp://119.28.133.11:61616";
        connectionFactory=new ActiveMQConnectionFactory(url);

        try {
            connection=connectionFactory.createConnection();
            connection.start();
            Session session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            Destination destination=session.createQueue("test");
            MessageProducer producer=session.createProducer(destination);
            TextMessage textMessage=session.createTextMessage();
            for (int i=1;i<11;i++){
                String tmp=message+" "+i;
                textMessage.setStringProperty("mySubect",tmp);
                producer.send(textMessage);
                Thread.sleep(2000);
                session.commit();
                System.out.println("message sent:"+tmp);
            }
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
