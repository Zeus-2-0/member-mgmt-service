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
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 01, July 2022
 * Time: 7:09 PM
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
@Table(name = "ENROLLMENT_SPAN")
public class EnrollmentSpan {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "enrlmnt_span_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID enrollmentSpanSK;

    /**
     * The unique code for the enrollment span in MMS
     */
    @Column(name = "enrollment_span_code", columnDefinition = "varchar", length = 50, updatable = false, nullable = false)
    private String enrollmentSpanCode;

    /**
     * The account that the enrollment span is associated with
     */
    @ManyToOne
    @JoinColumn(name = "account_sk", nullable = false, columnDefinition = "varchar")
    private Account account;

    /**
     * The list of all the premium spans associated with the enrollment span
     */
    @OneToMany(mappedBy = "enrollmentSpan", fetch = FetchType.EAGER)
    private Set<PremiumSpan> premiumSpans;

    /**
     * The state for which the enrollment span is created
     */
    @Column(name = "state_type_code", length = 45, columnDefinition = "varchar", nullable = false)
    private String stateTypeCode;

    /**
     * The marketplace associated with the enrollment span
     */
    @Column(name = "marketplace_type_code", length = 45, columnDefinition = "varchar", nullable = false)
    private String marketplaceTypeCode;

    /**
     * The business unit associated with the enrollment span
     */
    @Column(name = "business_unit_type_code", length = 50, columnDefinition = "varchar", nullable = false)
    private String businessUnitTypeCode;

    /**
     * Start date of the enrollment span
     */
    @Column(name = "start_date", columnDefinition = "datetime", nullable = false)
    private LocalDate startDate;

    /**
     * End date of the enrollment span
     */
    @Column(name = "end_date", columnDefinition = "datetime", nullable = false)
    private LocalDate endDate;

    /**
     * The exchange subscriber id associated with the enrollment span
     */
    @Column(name = "exchange_subscriber_id", columnDefinition = "varchar", nullable = false, length = 50)
    private String exchangeSubscriberId;

    /**
     * Date when the enrollment span was effectuated
     */
    @Column(name = "effectuation_date", columnDefinition = "datetime", nullable = true)
    private LocalDate effectuationDate;

    /**
     * The plan of the enrollment span. This is the 14 character QHP ID
     */
    @Column(name = "plan_id", length = 100, columnDefinition = "varchar", nullable = false)
    private String planId;

    /**
     * The group policy id of the enrollment span
     */
    @Column(name = "group_policy_id", length = 100, columnDefinition = "varchar", nullable = false)
    private String groupPolicyId;

    /**
     * The product type code associated with the enrollment span
     */
    @Column(name = "product_type_code", length = 100, columnDefinition = "varchar", nullable = false)
    private String productTypeCode;

    /**
     * The status of the enrollment span
     */
    @Column(name = "status_type_code", length = 50, columnDefinition = "varchar", nullable = false)
    private String statusTypeCode;

    /**
     * Identifies if the enrollment span is delinquent or not
     */
    @Column(name="delinq_ind")
    private boolean delinqInd;

    /**
     * The paid through date of the enrollment span
     */
    @Column(name = "paid_through_date", nullable = true)
    private LocalDate paidThroughDate;

    /**
     * The claim paid through date of the enrollment span
     */
    @Column(name = "claim_paid_through_date", nullable = true)
    private LocalDate claimPaidThroughDate;

    /**
     * The zeus transaction control number of the transaction that created the enrollment span
     */
    @Column(name = "ztcn", length = 20, columnDefinition = "varchar", nullable = false)
    private String ztcn;

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
     * to string method
     * @return
     */
    @Override
    public String toString() {
        return "EnrollmentSpan{" +
                "enrollmentSpanSK=" + enrollmentSpanSK +
                ", enrollmentSpanCode='" + enrollmentSpanCode + '\'' +
                ", account=" + account +
                ", premiumSpans=" + premiumSpans +
                ", stateTypeCode='" + stateTypeCode + '\'' +
                ", marketplaceTypeCode='" + marketplaceTypeCode + '\'' +
                ", businessUnitTypeCode='" + businessUnitTypeCode + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", exchangeSubscriberId='" + exchangeSubscriberId + '\'' +
                ", effectuationDate=" + effectuationDate +
                ", planId='" + planId + '\'' +
                ", groupPolicyId='" + groupPolicyId + '\'' +
                ", productTypeCode='" + productTypeCode + '\'' +
                ", statusTypeCode='" + statusTypeCode + '\'' +
                ", delinqInd=" + delinqInd +
                ", ztcn='" + ztcn + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    /**
     * equals method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentSpan that = (EnrollmentSpan) o;
        return Objects.equals(enrollmentSpanSK, that.enrollmentSpanSK);
    }

    /**
     * hashcode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(enrollmentSpanSK);
    }
}
