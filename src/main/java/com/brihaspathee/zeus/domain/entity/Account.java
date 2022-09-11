package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "account_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID accountSK;

    /**
     * The list of all the members associated with the account
     */
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Member> members;

    /**
     * The list of all the enrollment spans associated with the account
     */
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<EnrollmentSpan> enrollmentSpans;

    /**
     * The list of all the attributes associated with the account
     */
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<AccountAttribute> accountAttributes;

    /**
     * Unique number associated with each account
     */
    @Column(name = "account_number", nullable = false)
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
     * toString method
     * @return
     */
    @Override
    public String toString() {
        return "Account{" +
                "accountSK=" + accountSK +
                ", members=" + members +
                ", enrollmentSpans=" + enrollmentSpans +
                ", accountAttributes=" + accountAttributes +
                ", accountNumber='" + accountNumber + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    /**
     * the equals method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountSK.equals(account.accountSK);
    }

    /**
     * hascode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountSK);
    }
}
