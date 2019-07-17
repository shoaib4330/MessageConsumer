package com.kashegypt.gateway.notification.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class SmtpConfig {
    @Value("${mail.provider.host}")
    private String host;

    @Value("${mail.provider.port}")
    private Integer port;

    @Value("${mail.provider.ssl}")
    private Boolean sslEnabled;

    @Value("${mail.provider.tls}")
    private Boolean tlsEnables;

    @Value("${mail.provider.user}")
    private String username;

    @Value("${mail.provider.password}")
    private String password;

    @Bean
    public Session session() {
        return Session.getInstance(properties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", String.valueOf(port));
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        return properties;
    }
}
