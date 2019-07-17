package com.kashegypt.gateway.notification.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

@Component
public class HashMapConverter implements AttributeConverter<Map<String, String>, String> {

    private final Logger log = LoggerFactory.getLogger(HashMapConverter.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> attributeMap) {
        String jsonText = null;
        try {
            jsonText = objectMapper.writeValueAsString(attributeMap);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return jsonText;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String jsonString) {
        if(jsonString==null || jsonString.isEmpty()){
            return null;
        }
        Map<String, String> info = null;
        try {
            info = objectMapper.readValue(jsonString, Map.class);
        } catch (final IOException e) {
            log.error("Json to entity error", e);
        }
        return info;
    }

}
