package com.kashegypt.gateway.notification.mail;

import com.google.common.collect.ImmutableMap;
import com.kashegypt.gateway.notification.mail.jpa.EmailRepository;
import com.kashegypt.gateway.notification.mail.jpa.MailTemplateRepository;
import com.kashegypt.gateway.notification.mail.jpa.model.MailTemplate;
import com.kashegypt.gateway.notification.mail.jpa.model.To;
import com.kashegypt.gateway.notification.mail.mapper.MailMapper;
import com.kashegypt.gateway.notification.mail.dto.MailMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Map;

@Component
@Validated
public class MailQueueListener {

    @Autowired
    private MailService mailService;

    @Autowired
    private MailTemplateRepository mailTemplateRepository;

    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private EmailRepository emailRepository;

    @Value("${ke.app.link}")
    private String keAppLink;

    @JmsListener(destination = "${jms.queue.destination.mail}", containerFactory = "mailListenerContainerFactory")
    public void onEmailRequestMessage(final @Valid MailMessageRequest message, @Header(name = JmsHeaders.REDELIVERED) Boolean redelivered) {
        MailTemplate mailTemplate = mailTemplateRepository.findByNameAndLanguageId(message.getTemplateName(), message.getLanguageId()).orElseThrow(()->new RuntimeException("Template not found"));
        message.getTo().stream().forEach(user->sendEmail(user, mailTemplate, message, redelivered));
    }

    private void sendEmail(To recipient, MailTemplate mailTemplate, MailMessageRequest mailMessageRequest, Boolean redelivered){
        // mail Title populated
        String mailTitleContent = mailMapper.mapDataToPlaceHolders(mailTemplate.getMailTitleDataHolder(), createTitleDataMap(recipient));

        // mail body populated
        String mailBodyContent = mailMapper.mapDataToPlaceHolders(mailTemplate.getMailBodyDataHolder(), recipient.getAdditionalData());

        // html-styled body, with html
        String mailBodyContentHtmlStyled = createMailBody(mailTemplate.getContainingHtml(), mailTitleContent, mailBodyContent);

        mailService.email(mailMessageRequest, mailTemplate.getSubject(), mailBodyContentHtmlStyled);
        if (!redelivered) {
            emailRepository.save(mailMapper.map(mailTemplate.getName(), mailTemplate.getSubject(), mailTitleContent, mailBodyContent, mailMessageRequest.getFrom(), mailMessageRequest.getTo(), mailMessageRequest.getCc(), mailMessageRequest.getBcc(), mailMessageRequest.getLanguageId(), recipient.getAdditionalData(), recipient.getUserId()));
        }
    }

    private Map<String, String> createTitleDataMap(To recipient){
        return ImmutableMap.<String, String>builder()
                .put("NAME", recipient.getName())
                .build();
    }

    private String createMailBody(String html, String titleContent, String bodyContent){
        return mailMapper.mapDataToPlaceHolders(html, ImmutableMap.<String, String>builder()
                .put("TITLE", titleContent)
                .put("MAILBODY", bodyContent)
                .put("APPLINK", keAppLink)
                .build());
    }
}
