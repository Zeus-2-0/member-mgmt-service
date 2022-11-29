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
@Table(name = "MEMBER_ADDRESS")
public class MemberAddress {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "member_address_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID memberAddressSK;

    /**
     * The unique code for the member address in MMS
     */
    @Column(name = "member_address_code", columnDefinition = "varchar", length = 50, updatable = false, nullable = false)
    private String memberAddressCode;

    /**
     * The member that is associated with the address
     */
    @ManyToOne
    @JoinColumn(name="member_sk")
    private Member member;

    /**
     * The address type code of the address
     */
    @Column(name = "address_type_code", columnDefinition = "varchar", nullable = false, length = 20)
    private String addressTypeCode;

    /**
     * The address line 1 of the address
     */
    @Column(name = "address_line_1", columnDefinition = "varchar", nullable = false, length = 100)
    private String addressLine1;

    /**
     * The address line 2 of the address
     */
    @Column(name = "address_line_2", columnDefinition = "varchar", nullable = true, length = 100)
    private String addressLine2;

    /**
     * The city of the address
     */
    @Column(name = "city", columnDefinition = "varchar", nullable = false, length = 100)
    private String city;

    /**
     * The state of the address
     */
    @Column(name = "state_type_code", columnDefinition = "varchar", nullable = false, length = 20)
    private String stateTypeCode;

    /**
     * The zip code of the address
     */
    @Column(name = "zip_code", columnDefinition = "varchar", nullable = false, length = 10)
    private String zipCode;

    /**
     * The start date of the address
     */
    @Column(name = "start_date", columnDefinition = "datetime", nullable = false)
    private LocalDate startDate;

    /**
     * The end date of the address
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
     * to string method
     * @return
     */
    @Override
    public String toString() {
        return "MemberAddress{" +
                "memberAddressSK=" + memberAddressSK +
                ", addressTypeCode='" + addressTypeCode + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", stateTypeCode='" + stateTypeCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
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
        MemberAddress that = (MemberAddress) o;
        return Objects.equals(memberAddressSK, that.memberAddressSK);
    }

    /**
     * hashcode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(memberAddressSK);
    }
}
