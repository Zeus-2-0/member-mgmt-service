package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
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
@Table(name = "ACCOUNT_ATTRIBUTES")
public class AccountAttribute {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "acct_attribute_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID accountAttributeSK;

    /**
     * The attribute associated with the account
     */
    @ManyToOne
    @JoinColumn(name = "attribute_sk")
    private Attribute attribute;

    /**
     * The account of the attribute
     */
    @ManyToOne
    @JoinColumn(name = "account_sk")
    private Account account;

    /**
     * The value that is assigned to the attribute
     */
    @Column(name = "attribute_value")
    private String attributeValue;

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
     * to String method
     * @return
     */
    @Override
    public String toString() {
        return "AccountAttribute{" +
                "accountAttributeSK=" + accountAttributeSK +
                ", attribute=" + attribute +
                ", attributeValue='" + attributeValue + '\'' +
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
        AccountAttribute that = (AccountAttribute) o;
        return Objects.equals(accountAttributeSK, that.accountAttributeSK);
    }

    /**
     * hashcode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountAttributeSK);
    }
}
