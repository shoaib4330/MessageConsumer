package com.kashegypt.gateway.notification.sms.jpa.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "smsTemplate")
public class SMSTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private int languageId;
    private String name;
    private String body;
    private Date createdTimestamp;
    private Date updatedTimestamp;
    private Boolean isActive;

}
