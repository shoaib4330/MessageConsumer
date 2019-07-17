package com.kashegypt.gateway.notification.common.jpa.model;

import com.kashegypt.gateway.notification.converter.HashMapConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Date createdTimestamp;
    private Date updatedTimestamp;
    @Builder.Default
    private Boolean isActive = true;
    private Long userId;
    private int notificationType;
    private int communicationType;
    private Long templateId;
    private String title;
    private String body;
    private String topicName;
    @Convert(converter = HashMapConverter.class)
    private Map<String, String> additionalData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "templateId", insertable = false, updatable = false)
    private NotificationTemplate notificationTemplate;
}
