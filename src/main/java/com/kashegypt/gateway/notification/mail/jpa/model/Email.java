package com.kashegypt.gateway.notification.mail.jpa.model;

import com.kashegypt.gateway.notification.converter.HashMapConverter;
import com.kashegypt.gateway.notification.mail.converter.ListConverter;
import com.kashegypt.gateway.notification.mail.converter.ToConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "email")
public class Email implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    @Convert(converter = ListConverter.class)
    private List<To> receiver;
    @Convert(converter = ToConverter.class)
    private To cc;
    @Convert(converter = ToConverter.class)
    private To bcc;

    private Long languageId;
    private String templateName;
    private Long userId;
    private String subject;
    private String title;
    private String body;
    @Convert(converter = HashMapConverter.class)
    private Map<String, String> mailBodyData;

    private Date createdTimestamp;
    private Date updatedTimestamp;
    @Builder.Default
    private Boolean isActive = true;
}
