package com.kashegypt.gateway.notification.jms.consumer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kashegypt.gateway.notification.jms.consumer.model.validator.annotation.Enum;
import com.kashegypt.gateway.notification.jms.consumer.model.validator.type.Language;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushNotificationRequestMessage implements Serializable {
    private static final long serialVersionUID = -1L;

    @NotBlank(message = "templateName must not be null or blank")
    private String templateName;

    private Map<String, String> placeHolderDataForBody;
    private Map<String, String> placeHolderDataForTitle;
    private Map<String, String> additionalData;

    @NotNull(message = "languageId must not be null")
    @Enum(enumClass = Language.class, matchId = true)
    private Integer languageId;

    @NotNull(message = "createdTimestamp must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdTimestamp;


}
