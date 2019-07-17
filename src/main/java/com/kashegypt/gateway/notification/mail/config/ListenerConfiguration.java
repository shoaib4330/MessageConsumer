package com.kashegypt.gateway.notification.mail.config;

import com.kashegypt.gateway.notification.mail.converter.MailMessageConverter;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.mail.Session;

@Configuration
public class ListenerConfiguration {

    @Value("${spring.artemis.host}")
    private String brokerUrl;

    @Value("${spring.artemis.user}")
    private String username;

    @Value("${spring.artemis.password}")
    private String password;

    @Value("${spring.artemis.port}")
    private String port;

    @Bean
    public JmsListenerContainerFactory mailListenerContainerFactory(){
        JmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        ((DefaultJmsListenerContainerFactory) jmsListenerContainerFactory).setConnectionFactory(new ActiveMQConnectionFactory("tcp://" + brokerUrl + ":61616", username, password));
        ((DefaultJmsListenerContainerFactory) jmsListenerContainerFactory).setMessageConverter(new MailMessageConverter());
        return jmsListenerContainerFactory;
    }
}
