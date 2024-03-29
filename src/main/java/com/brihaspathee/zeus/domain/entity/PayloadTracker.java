package com.brihaspathee.zeus.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import jakarta.persistence.*;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, September 2022
 * Time: 4:53 PM
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
@Table(name = "PAYLOAD_TRACKER")
public class PayloadTracker {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "payload_tracker_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID payloadTrackerSK;

    /**
     * Unique payload id created for the payload
     */
    @Column(name = "payload_id", length = 45, nullable = false)
    private String payloadId;

    /**
     * The account number for which the payload was sent
     */
    @Column(name = "acct_number", columnDefinition = "varchar", length = 50, nullable = false)
    private String accountNumber;

    /**
     * The payload data in JSON format
     */
    @Lob
    @JdbcTypeCode(Types.LONGVARCHAR)
    @Column(name = "payload")
    private String payload;

    /**
     * Identifies if the payload is an inbound or outbound payload
     */
    @Column(name = "payload_direction_type_code", length = 45, nullable = false)
    private String payloadDirectionTypeCode;

    /**
     * The source of the payload when its an inbound payload and the
     * destination of the payload when it is an outbound payload
     */
    @Column(name = "src_dest", length=100, nullable = false)
    private String sourceDestinations;

    /**
     * The detail tracker of the payload
     */
    @OneToMany(mappedBy = "payloadTracker", cascade = CascadeType.REMOVE)
    private Set<PayloadTrackerDetail> payloadTrackerDetails;

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
        return "PayloadTracker{" +
                "payloadTrackerSK=" + payloadTrackerSK +
                ", payloadId='" + payloadId + '\'' +
                ", payload='" + payload + '\'' +
                ", payloadDirectionTypeCode='" + payloadDirectionTypeCode + '\'' +
                ", sourceDestinations='" + sourceDestinations + '\'' +
                ", payloadTrackerDetails=" + payloadTrackerDetails +
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
        PayloadTracker that = (PayloadTracker) o;
        return Objects.equals(payloadTrackerSK, that.payloadTrackerSK);
    }

    /**
     * hashcode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(payloadTrackerSK);
    }
}
