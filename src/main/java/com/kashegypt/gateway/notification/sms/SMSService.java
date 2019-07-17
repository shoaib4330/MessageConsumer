package com.kashegypt.gateway.notification.sms;

import com.kashegypt.gateway.notification.sms.request.SMSRequestMessage;
import com.kashegypt.gateway.notification.sms.response.SMSResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SMSService {
    private final Logger log = LoggerFactory.getLogger(SMSService.class);

    @Value("${cequens.sms.url}")
    private String cequensUrl;

    @Value("${cequens.append.country.code}")
    private boolean appendCountryCode;

    @Value("${cequens.country.code}")
    private String cequensCountryCode;

    @Autowired
    private RestTemplate smsRestTemplate;

    public void sendSMS(SMSRequestMessage requestMessage) {

        if (appendCountryCode) {
            log.info("cequens.append.country.code is set to true so appending configured country code {}", cequensCountryCode);
            requestMessage.setRecipients(cequensCountryCode.concat(requestMessage.getRecipients()));
        }
        SMSResponseMessage response = smsRestTemplate.postForObject(cequensUrl, requestMessage, SMSResponseMessage.class);
        log.info("Successfully sent SMS, response received : {}", response);
    }
}
