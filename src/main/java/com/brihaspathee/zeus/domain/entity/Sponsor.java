package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 28, November 2022
 * Time: 2:36 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.entity
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SPONSOR")
public class Sponsor {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "sponsor_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID sponsorSK;

    /**
     * The unique code that is assigned to the sponsor in MMS
     */
    @Column(name = "sponsor_code", length = 50, columnDefinition = "varchar", nullable = false)
    private String sponsorCode;

    /**
     * The account that the sponsor is associated with
     */
    @ManyToOne
    @JoinColumn(name = "account_sk", nullable = false, columnDefinition = "varchar")
    private Account account;

    /**
     * The sponsor id of the sponsor
     */
    @Column(name = "sponsor_id", length = 50, columnDefinition = "varchar", nullable = false)
    private String sponsorId;

    /**
     * The name of the sponsor
     */
    @Column(name = "sponsor_name", columnDefinition = "varchar", updatable = true, nullable = false)
    private String sponsorName;

    /**
     * The start date of the sponsor
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * The end date of the sponsor
     */
    @Column(name = "end_date")
    private LocalDate endDate;

    /**
     * Date when the record was created
     */
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * Date and time when the record was updated
     */
    @CreationTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() {
        return "Sponsor{" +
                "sponsorSK=" + sponsorSK +
                ", sponsorCode='" + sponsorCode + '\'' +
                ", sponsorId='" + sponsorId + '\'' +
                ", sponsorName='" + sponsorName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
