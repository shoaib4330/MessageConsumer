package com.kashegypt.gateway.notification.common.jpa.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="notificationTemplate")
public class NotificationTemplate implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdTimestamp;
    private Date updatedTimestamp;
    @Builder.Default
    private Boolean isActive = true;
    private String name;
    private String title;
    private String body;
    private int languageId;

    private int notificationType;
}
