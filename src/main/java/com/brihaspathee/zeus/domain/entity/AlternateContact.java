package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 28, November 2022
 * Time: 2:15 PM
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
@Table(name = "ALTERNATE_CONTACT")
public class AlternateContact {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "alternate_contact_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID alternateContactSK;

    /**
     * The member that is associated with the alternate contact
     */
    @ManyToOne
    @JoinColumn(name="member_sk")
    private Member member;

    /**
     * The unique code in MMS for alternate contact
     */
    @Column(name = "alternate_contact_code", length = 50, columnDefinition = "varchar", nullable = false)
    private String alternateContactCode;

    /**
     * The type of alternate contact
     */
    @Column(name = "alternate_contact_type_code")
    private String alternateContactTypeCode;

    /**
     * The first name of the alternate contact address
     */
    @Column(name = "first_name", columnDefinition = "varchar", length = 100, nullable = true)
    private String firstName;

    /**
     * The middle name of the alternate contact address
     */
    @Column(name = "middle_name", columnDefinition = "varchar", length = 50, nullable = false)
    private String middleName;

    /**
     * The last name of the alternate contact address
     */
    @Column(name = "last_name", columnDefinition = "varchar", length = 100, nullable = false)
    private String lastName;

    /**
     * The identifier type of the alternate contact address
     */
    @Column(name = "identifier_type_code", columnDefinition = "varchar", length = 50, nullable = true)
    private String identifierTypeCode;

    /**
     * The identifier of the alternate contact address
     */
    @Column(name = "identifier_value", columnDefinition = "varchar", length = 100, nullable = true)
    private String identifierValue;

    /**
     * The phone type of the alternate contact address
     */
    @Column(name = "phone_type_code", columnDefinition = "varchar", length = 50, nullable = true)
    private String phoneTypeCode;

    /**
     * The phone number of the alternate contact address
     */
    @Column(name = "phone_number", columnDefinition = "varchar", length = 50, nullable = true)
    private String phoneNumber;

    /**
     * The email of the alternate contact address
     */
    @Column(name = "email", columnDefinition = "varchar", length = 100, nullable = true)
    private String email;

    /**
     * The address line 1 of the alternate contact address
     */
    @Column(name = "address_line_1", columnDefinition = "varchar", length = 100, nullable = true)
    private String addressLine1;

    /**
     * The address line 2 of the alternate contact address
     */
    @Column(name = "address_line_2", columnDefinition = "varchar", length = 50, nullable = true)
    private String addressLine2;

    /**
     * The city of the alternate contact address
     */
    @Column(name = "city", columnDefinition = "varchar", length = 50, nullable = true)
    private String city;

    /**
     * The state of the alternate contact address
     */
    @Column(name = "state_type_code", columnDefinition = "varchar", length = 50, nullable = true)
    private String stateTypeCode;

    /**
     * The zip code of the alternate contact address
     */
    @Column(name = "zip_code", columnDefinition = "varchar", length = 50, nullable = true)
    private String zipCode;

    /**
     * Start date of the alternate contact
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * End date of the alternate contact
     */
    @Column(name = "end_date")
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
}
