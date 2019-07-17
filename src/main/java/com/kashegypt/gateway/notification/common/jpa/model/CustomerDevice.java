package com.kashegypt.gateway.notification.common.jpa.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "userDevice")
public class CustomerDevice implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Date createdTimestamp;
    protected Date updatedTimestamp;
    @Builder.Default
    protected Boolean isActive = true;

    private String deviceIMEI;

    private String brand;

    private String model;

    private String operatingSystem;

    private String networkOperator;

    private Long customerAccountId;

    private String fcmDeviceRegistrationId;

}
