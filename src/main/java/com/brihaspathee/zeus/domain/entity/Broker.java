package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
@Table(name = "BROKER")
public class Broker {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "broker_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID brokerSK;

    /**
     * The unique code assigned to the broker in MMS
     */
    @Column(name = "broker_code", length = 50, columnDefinition = "varchar", nullable = false)
    private String brokerCode;

    /**
     * The account that the broker is associated with
     */
    @ManyToOne
    @JoinColumn(name = "account_sk", nullable = false, columnDefinition = "varchar")
    private Account account;

    /**
     * The id of the broker
     */
    @Column(name = "broker_id", length = 100, columnDefinition = "varchar", nullable = false)
    private String brokerId;

    /**
     * The name of the broker
     */
    @Column(name = "broker_name", length = 100, columnDefinition = "varchar", nullable = false)
    private String brokerName;

    /**
     * The agency id of the broker
     */
    @Column(name = "agency_id", length = 50, columnDefinition = "varchar", nullable = true)
    private String agencyId;

    /**
     * The agency name of the broker
     */
    @Column(name = "agency_name", length = 100, columnDefinition = "varchar", nullable = true)
    private String agencyName;

    /**
     * The account number 1 of the broker
     */
    @Column(name = "account_number_1", length = 50, columnDefinition = "varchar", nullable = true)
    private String accountNumber1;

    /**
     * The account number 2 of the broker
     */
    @Column(name = "account_number_2", length = 50, columnDefinition = "varchar", nullable = true)
    private String accountNumber2;

    /**
     * The start date of the broker
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * The end date of the broker
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
}
