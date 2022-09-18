package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
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
@Table(name = "PREMIUM_SPAN")
public class PremiumSpan {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "premium_span_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID premiumSpanSK;

    /**
     * The enrollment span associated with the premium span
     */
    @ManyToOne
    @JoinColumn(name = "enrollment_span_sk")
    private EnrollmentSpan enrollmentSpan;

    /**
     * The members associated with the enrollment span
     */
    @OneToMany(mappedBy = "premiumSpan", fetch = FetchType.EAGER)
    private Set<MemberPremium> members;

    /**
     * Start date of the premium span
     */
    @Column(name = "start_date", columnDefinition = "datetime", nullable = false)
    private LocalDate startDate;

    /**
     * End date of the premium span
     */
    @Column(name = "end_date", columnDefinition = "datetime", nullable = false)
    private LocalDate endDate;

    /**
     * The csr variant associated with the premium span
     */
    @Column(name = "csr_variant", length=10, columnDefinition = "varchar", nullable = false)
    private String csrVariant;

    /**
     * The total premium amount associated with the premium span
     */
    @Column(name = "total_prem_amt", nullable = false)
    private BigDecimal totalPremiumAmount;

    /**
     * The total responsible amount associated with the premium span
     */
    @Column(name = "total_resp_amt", nullable = false)
    private BigDecimal totalResponsibleAmount;

    /**
     * The APTC amount associated with the premium span
     */
    @Column(name = "aptc_amt", nullable = false)
    private BigDecimal aptcAmount;

    /**
     * The other pay amount associated with the premium span
     */
    @Column(name = "other_pay_amt", nullable = false)
    private BigDecimal otherPayAmount;

    /**
     * The CSR amount associated with the premium span
     */
    @Column(name = "csr_amt", nullable = false)
    private BigDecimal csrAmount;

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


//    /**
//     * equals method
//     * @param o
//     * @return
//     */
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        PremiumSpan that = (PremiumSpan) o;
//        return premiumSpanSK.equals(that.premiumSpanSK);
//    }
//
//    /**
//     * hashcode method
//     * @return
//     */
//    @Override
//    public int hashCode() {
//        return Objects.hash(premiumSpanSK);
//    }
}
