package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;

import java.sql.Types;
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
@Table(name = "PAYER")
public class Payer {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "payer_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID payerSK;

    /**
     * A unique code assigned to the payer in MMS
     */
    @Column(name = "payer_code", columnDefinition = "varchar", length = 50, nullable = false)
    private String payerCode;

    /**
     * The account that the payer is associated with
     */
    @ManyToOne
    @JoinColumn(name = "account_sk", nullable = false, columnDefinition = "varchar")
    private Account account;

    /**
     * The payer id of the payer
     */
    @Column(name = "payer_id", columnDefinition = "varchar", length = 50, nullable = false)
    private String payerId;

    /**
     * The name of the payer
     */
    @Column(name = "payer_name")
    private String payerName;

    /**
     * The zeus transaction control number of the transaction that created the payer
     */
    @Column(name = "ztcn", length = 50, columnDefinition = "varchar", nullable = true)
    private String ztcn;

    /**
     * The source of the data
     */
    @Column(name = "source", length = 50, columnDefinition = "varchar", nullable = false)
    private String source;

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
}
