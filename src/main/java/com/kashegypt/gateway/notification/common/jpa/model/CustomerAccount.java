package com.kashegypt.gateway.notification.common.jpa.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customerAccount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class CustomerAccount extends UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean sinchVerified = false;

    private Boolean signUpQuestionsAnswered = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date questionsAnsweredDate;

    private String profileImagePath;

    private int idCardDetailsId;

    private String signUpLocation;

    private String signUpChannel;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerAccountId")
    private Set<CustomerDevice> customerDevices;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Date createdTimestamp;
    protected Date updatedTimestamp;

    protected Boolean isActive = true;


}
