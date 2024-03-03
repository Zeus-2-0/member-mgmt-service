package com.brihaspathee.zeus.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import jakarta.persistence.*;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, April 2022
 * Time: 4:11 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class Account {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "account_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID accountSK;

    /**
     * Unique number associated with each account
     */
    @Column(name = "acct_number", nullable = false)
    private String accountNumber;

    /**
     * The line of business associated with the account
     */
    @Column(name = "line_of_business_type_code", columnDefinition = "varchar", length = 45, nullable = false)
    private String lineOfBusinessTypeCode;

    /**
     * The date when the record was created
     */
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * The date when the record was updated
     */
    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    /**
     * The zeus transaction control number of the transaction that created the account
     */
    @Column(name = "ztcn", length = 50, columnDefinition = "varchar", nullable = true)
    private String ztcn;

    /**
     * The source of the data
     */
    @Column(name = "source", length = 50, columnDefinition = "varchar", nullable = false)
    private String source;

    /**
     * The list of all the members associated with the account
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<Member> members;

    /**
     * The list of all the enrollment spans associated with the account
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<EnrollmentSpan> enrollmentSpans;

    /**
     * The list of all the brokers associated with the account
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<Broker> brokers;

    /**
     * The list of all the payers associated with the account
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<Payer> payers;

    /**
     * The list of all the sponsors associated with the account
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<Sponsor> sponsors;

    /**
     * The list of all the attributes associated with the account
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<AccountAttribute> accountAttributes;


    /**
     * toString method
     * @return returns a string
     */
    @Override
    public String toString() {
        return "Account{" +
                "accountSK=" + accountSK +
                ", accountNumber='" + accountNumber + '\'' +
                ", lineOfBusinessTypeCode='" + lineOfBusinessTypeCode + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", ztcn='" + ztcn + '\'' +
//                ", members=" + members +
//                ", enrollmentSpans=" + enrollmentSpans +
//                ", brokers=" + brokers +
//                ", payers=" + payers +
//                ", sponsors=" + sponsors +
//                ", accountAttributes=" + accountAttributes +
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
        Account account = (Account) o;
        return Objects.equals(accountSK, account.accountSK);
    }

    /**
     * hashcode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountSK);
    }
}
