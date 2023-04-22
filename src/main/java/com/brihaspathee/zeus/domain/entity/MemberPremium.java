package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 01, July 2022
 * Time: 7:10 PM
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
@Table(name = "MEMBER_PREMIUMS")
public class MemberPremium {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "member_premium_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID memberPremiumSK;

    /**
     * The premium span associated with the member
     */
    @ManyToOne
    @JoinColumn(name = "premium_span_sk")
    private PremiumSpan premiumSpan;

    /**
     * The member associated with the premium span
     */
    @ManyToOne
    @JoinColumn(name = "member_sk")
    private Member member;

    /**
     * The exchange member id associated with the member
     */
    @Column(name = "exchange_member_id", length=50, columnDefinition = "varchar", nullable = false)
    private String exchangeMemberId;

    /**
     * The premium amount for the member
     */
    @Column(name = "individual_premium_amount", nullable = false)
    private BigDecimal individualPremiumAmount;

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
        return "MemberPremium{" +
                "memberPremiumSK=" + memberPremiumSK +
                ", premiumSpan=" + premiumSpan +
                ", member=" + member +
                ", individualPremiumAmount=" + individualPremiumAmount +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MemberPremium that = (MemberPremium) o;
//        return memberPremiumSK.equals(that.memberPremiumSK);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(memberPremiumSK);
//    }
}
