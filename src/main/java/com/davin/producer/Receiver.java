package com.davin.producer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Author :davin
 * Date 2017/9/2
 * For More Information,Please Visit https://github.com/wudeyong
 */
public class Receiver {

    public static void receiver(){
        String url="tcp://119.28.133.11:61616";
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        connectionFactory=new ActiveMQConnectionFactory(url);
        try {
            connection=connectionFactory.createConnection();
//            开始一个连接，
            connection.start();
            session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            Destination destination=session.createQueue("com.davin.tool");
            MessageConsumer messageConsumer=session.createConsumer(destination);
//
            while(true){
                TextMessage message=(TextMessage) messageConsumer.receive();
                //message.getShortProperty("mySubect");
                if (message!=null){
                    System.out.println(message.getStringProperty("mySubect"));
                    System.out.print(message.getJMSMessageID()+"\t");
                    System.out.println(message.getJMSTimestamp());
                }else break;
//
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
