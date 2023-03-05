package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, September 2022
 * Time: 6:49 AM
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
@Table(name = "ATTRIBUTE")
public class Attribute {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "attribute_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID attributeSK;

    /**
     * Unique attribute code for the attribute
     */
    @Column(name = "attribute_code")
    private String attributeCode;

    /**
     * Name of the attribute
     */
    @Column(name = "attribute_name")
    private String attributeName;

    /**
     * Type of attribute, it is either "Defined" or "Custom"
     */
    @Column(name = "attribute_type")
    private String attributeType;

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
     * To string method
     * @return
     */
    @Override
    public String toString() {
        return "Attribute{" +
                "attributeSK=" + attributeSK +
                ", attributeCode='" + attributeCode + '\'' +
                ", attributeName='" + attributeName + '\'' +
                ", attributeType='" + attributeType + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
