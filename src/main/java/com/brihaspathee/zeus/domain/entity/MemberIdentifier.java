package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 01, July 2022
 * Time: 7:11 PM
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
@Table(name = "MEMBER_IDENTIFIER")
public class MemberIdentifier {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "member_identifier_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID memberIdentifierSK;

    /**
     * The unique code for the member identifier in MMS
     */
    @Column(name = "member_identifier_code", columnDefinition = "varchar", length = 50, updatable = false, nullable = false)
    private String memberIdentifierCode;

    /**
     * The member to whom the identifier is associated
     */
    @ManyToOne
    @JoinColumn(name = "member_sk")
    private Member member;

    /**
     * Identifies the type of identifier
     */
    @Column(name = "identifier_type_code", length = 20, columnDefinition = "varchar", nullable = false)
    private String identifierTypeCode;

    /**
     * The identifier of the member
     */
    @Column(name = "identifier_value", length = 50, columnDefinition = "varchar", nullable = false)
    private String identifierValue;

    /**
     * The zeus transaction control number of the transaction that created the identifier
     */
    @Column(name = "ztcn", length = 50, columnDefinition = "varchar", nullable = true)
    private String ztcn;

    /**
     * The source of the data
     */
    @Column(name = "source", length = 50, columnDefinition = "varchar", nullable = false)
    private String source;

    /**
     * Identifies if the identifier is active
     */
    @Column(name="is_Active")
    private boolean isActive;

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
        return "MemberIdentifier{" +
                "memberIdentifierSK=" + memberIdentifierSK +
                ", identifierTypeCode='" + identifierTypeCode + '\'' +
                ", identifierValue='" + identifierValue + '\'' +
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
        MemberIdentifier that = (MemberIdentifier) o;
        return Objects.equals(memberIdentifierSK, that.memberIdentifierSK);
    }

    /**
     * hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(memberIdentifierSK);
    }
}
