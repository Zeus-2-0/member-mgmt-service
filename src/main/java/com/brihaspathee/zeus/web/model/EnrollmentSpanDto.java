package com.brihaspathee.zeus.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 09, September 2022
 * Time: 7:16 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentSpanDto {

    /**
     * The primary key of the record
     */
    @JsonProperty(required = false)
    @Schema(description = "Enrollment Span SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private UUID enrollmentSpanSK;

    /**
     * The account key of the account that the enrollment span is associated with
     */
    @JsonProperty(required = false)
    @Schema(description = "Account SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    @Null
    private UUID accountSK;

    /**
     * The premium spans associated with the enrollment span
     */
    @JsonProperty(required = true)
    private Set<PremiumSpanDto> premiumSpans;

    /**
     * The state associated with the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "State associated with the enrollment span", example = "FL", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String stateTypeCode;

    /**
     * The marketplace associated with the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "Marketplace associated with the enrollment span", example = "FFM", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String marketplaceTypeCode;

    /**
     * The business unit associated with the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "Business unit associated with the enrollment span", example = "FL_BU", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String businessUnitTypeCode;

    /**
     * The start date of the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "Start date associated with the enrollment span", example = "1/1/2022", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate startDate;

    /**
     * The end date of the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "End date associated with the enrollment span", example = "12/31/2022", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate endDate;

    /**
     * The effectuation date of the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "Effectuation date associated with the enrollment span", example = "12/14/2021", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate effectuationDate;

    /**
     * The plan id of the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "Plan Id associated with the enrollment span", example = "52345FL424525235", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String planId;

    /**
     * The group policy id of the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "Group policy id associated with the enrollment span", example = "523452345", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String groupPolicyId;

    /**
     * The product type code of the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "Product type code of the plan", example = "HMO", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String productTypeCode;

    /**
     * The status of the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "Status of the enrollment span", example = "ENROLLED", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String statusTypeCode;

    /**
     * The zeus transaction control number of the transaction that created the enrollment span
     */
    @JsonProperty(required = true)
    @Schema(description = "The zeus transaction control number of the transaction that created the enrollment span", example = "SDFG43456DFG23G", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String ztcn;

    /**
     * The date when the enrollment span was created
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the enrollment span was created", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime createdDate;

    /**
     * The date when the enrollment span was updated
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the enrollment span was updated", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime updatedDate;
}
