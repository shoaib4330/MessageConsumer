package com.kashegypt.gateway.notification.common.jpa.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userAccount")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public abstract class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private int status;

    private String primaryRoleId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Date createdTimestamp;
    protected Date updatedTimestamp;

    protected Boolean isActive = true;

    private String fullName;

    private String phoneNo;

    private String email;

    private int languageId;

    private String principalName;

    private boolean suspended = false;

}
