package com.kashegypt.gateway.notification.sms.jpa.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "sms")
public class SMS implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Long userId;
    private Long templateId;

    @CreationTimestamp
    private Date createdTimestamp;

    @UpdateTimestamp
    private Date updatedTimestamp;
    private String phoneNumber;
    private String body;

    @Builder.Default
    private Boolean isActive = true;
}
