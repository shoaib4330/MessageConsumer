package com.kashegypt.gateway.notification.mail.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kashegypt.gateway.notification.mail.jpa.model.To;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Component
public class ToConverter implements AttributeConverter<To, String> {

    private final Logger log = LoggerFactory.getLogger(ToConverter.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(To attributeMap) {
        String jsonText = null;
        try {
            jsonText = objectMapper.writeValueAsString(attributeMap);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return jsonText;
    }

    @Override
    public To convertToEntityAttribute(String jsonString) {
        if(jsonString==null || jsonString.isEmpty()){
            return null;
        }
        To info = null;
        try {
            info = objectMapper.readValue(jsonString, To.class);
        } catch (final IOException e) {
            log.error("Json to entity error", e);
        }
        return info;
    }

}
