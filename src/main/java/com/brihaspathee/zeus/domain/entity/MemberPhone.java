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
@Table(name = "MEMBER_PHONE")
public class MemberPhone {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "member_phone_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID memberPhoneSK;

    /**
     * The unique code for the member phone in MMS
     */
    @Column(name = "member_phone_code", columnDefinition = "varchar", length = 50, updatable = false, nullable = false)
    private String memberPhoneCode;

    /**
     * The member to whom the phone is associated
     */
    @ManyToOne
    @JoinColumn(name = "member_sk")
    private Member member;

    /**
     * Identifies the type of phone
     */
    @Column(name = "phone_type_code", length = 20, columnDefinition = "varchar", nullable = false)
    private String phoneTypeCode;

    /**
     * The phone number of the member
     */
    @Column(name = "phone_number", length = 30, columnDefinition = "varchar", nullable = false)
    private String phoneNumber;

    /**
     * The zeus transaction control number of the transaction that created the phone number
     */
    @Column(name = "ztcn", length = 50, columnDefinition = "varchar", nullable = true)
    private String ztcn;

    /**
     * The source of the data
     */
    @Column(name = "source", length = 50, columnDefinition = "varchar", nullable = false)
    private String source;

    /**
     * Start date of the phone number
     */
    @Column(name = "start_date", columnDefinition = "datetime", nullable = false)
    private LocalDate startDate;

    /**
     * End date of the phone number
     */
    @Column(name = "end_date", columnDefinition = "datetime", nullable = true)
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
        return "MemberPhone{" +
                "memberPhoneSK=" + memberPhoneSK +
                ", member=" + member +
                ", phoneTypeCode='" + phoneTypeCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
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
        MemberPhone that = (MemberPhone) o;
        return Objects.equals(memberPhoneSK, that.memberPhoneSK);
    }

    /**
     * hascode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(memberPhoneSK);
    }
}
