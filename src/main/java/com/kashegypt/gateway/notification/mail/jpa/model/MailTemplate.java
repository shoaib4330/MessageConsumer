package com.kashegypt.gateway.notification.mail.jpa.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "emailTemplate")
public class MailTemplate {
    @Id
    @GeneratedValue
    private Long id;
    private Long languageId;
    private String name;
    private String subject;
    private String containingHtml;
    private String mailTitleDataHolder;
    private String mailBodyDataHolder;
    private Date createdTimestamp;
    private Date updatedTimestamp;
    private Boolean isActive;

}
