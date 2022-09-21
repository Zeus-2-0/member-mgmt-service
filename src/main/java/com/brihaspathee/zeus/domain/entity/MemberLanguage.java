package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
@Table(name = "MEMBER_LANGUAGE")
public class MemberLanguage {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "member_language_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID memberLanguageSK;

    /**
     * The member to whom the language is associated
     */
    @ManyToOne
    @JoinColumn(name = "member_sk")
    private Member member;

    /**
     * Identifies the type of language
     */
    @Column(name = "language_type_code", length = 20, columnDefinition = "varchar", nullable = false)
    private String languageTypeCode;

    /**
     * The language of the member
     */
    @Column(name = "language_code", length = 30, columnDefinition = "varchar", nullable = false)
    private String languageCode;

    /**
     * Start date of the language
     */
    @Column(name = "start_date", columnDefinition = "datetime", nullable = false)
    private LocalDate startDate;

    /**
     * End date of the language
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
        return "MemberLanguage{" +
                "memberLanguageSK=" + memberLanguageSK +
                ", member=" + member +
                ", languageTypeCode='" + languageTypeCode + '\'' +
                ", languageCode='" + languageCode + '\'' +
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
        MemberLanguage that = (MemberLanguage) o;
        return Objects.equals(memberLanguageSK, that.memberLanguageSK);
    }

    /**
     * hashcode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(memberLanguageSK);
    }
}
