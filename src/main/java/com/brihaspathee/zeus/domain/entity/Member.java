package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;

import java.math.BigDecimal;
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
@Table(name = "MEMBER")
public class Member {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "member_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID memberSK;

    /**
     * The account of the member
     */
    @ManyToOne
    @JoinColumn(name = "account_sk")
    private Account account;

    /**
     * A unique code that is assigned to the member
     */
    @Column(name = "member_code", columnDefinition = "varchar", nullable = false, length = 15)
    private String memberCode;

    /**
     * The first name of the member
     */
    @Column(name = "first_name", columnDefinition = "varchar", nullable = false, length = 100)
    private String firstName;

    /**
     * The middle name of the member
     */
    @Column(name = "middle_name", columnDefinition = "varchar", nullable = true, length = 100)
    private String middleName;

    /**
     * The last name of the member
     */
    @Column(name = "last_name", columnDefinition = "varchar", nullable = false, length = 100)
    private String lastName;

    /**
     * The relationship of the member with the head of the household
     */
    @Column(name = "relationship_type_code", columnDefinition = "varchar", nullable = false, length = 20)
    private String relationshipTypeCode;

    /**
     * The date of birth of the member
     */
    @Column(name = "date_of_birth", columnDefinition = "datetime", nullable = false)
    private LocalDate dateOfBirth;

    /**
     * The gender type code of the member
     */
    @Column(name = "gender_type_code", columnDefinition = "varchar", nullable = false, length = 50)
    private String genderTypeCode;

    /**
     * The height of the member
     */
    @Column(name = "height", nullable = true)
    private BigDecimal height;

    /**
     * The weight of the member
     */
    @Column(name = "weight", nullable = true)
    private BigDecimal weight;

    /**
     * The zeus transaction control number of the transaction that created the member
     */
    @Column(name = "ztcn", length = 50, columnDefinition = "varchar", nullable = true)
    private String ztcn;

    /**
     * The source of the data
     */
    @Column(name = "source", length = 50, columnDefinition = "varchar", nullable = false)
    private String source;

    /**
     * The list of all the attributes associated with the member
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<MemberAttribute> memberAttributes;

    /**
     * The list of all the addresses associated with the member
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<MemberAddress> memberAddresses;

    /**
     * The list of all the identifiers associated with the member
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<MemberIdentifier> memberIdentifiers;

    /**
     * The list of all the emails associated with the member
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<MemberEmail> memberEmails;

    /**
     * The list of all the languages associated with the member
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<MemberLanguage> memberLanguages;

    /**
     * The list of all the phone numbers associated with the member
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<MemberPhone> memberPhones;

    /**
     * The list of all the premium spans associated with the member
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<MemberPremium> memberPremiums;

    /**
     * The list of all the alternate contacts associated with the member
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<AlternateContact> alternateContacts;

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
        return "Member{" +
                "memberSK=" + memberSK +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", genderTypeCode='" + genderTypeCode + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", memberAttributes=" + memberAttributes +
                ", memberAddresses=" + memberAddresses +
                ", memberIdentifiers=" + memberIdentifiers +
                ", memberEmails=" + memberEmails +
                ", memberLanguages=" + memberLanguages +
                ", memberPhones=" + memberPhones +
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
        Member member = (Member) o;
        return Objects.equals(memberSK, member.memberSK);
    }

    /**
     * hashcode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(memberSK);
    }
}
