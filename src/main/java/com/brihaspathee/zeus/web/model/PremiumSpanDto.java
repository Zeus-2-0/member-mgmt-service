package com.brihaspathee.zeus.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, September 2022
 * Time: 6:42 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PremiumSpanDto {

    /**
     * The primary key of the premium span
     */
    @JsonProperty(required = false)
    @Schema(description = "Premium Span SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private UUID premiumSpanSK;

    /**
     * The enrollment span that the premium span is associated
     */
    @JsonProperty(required = false)
    @Schema(description = "Enrollment Span SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    private UUID enrollmentSpanSK;

    /**
     * The list of members associated with the premium span
     */
    @JsonProperty(required = true)
    private Set<MemberPremiumDto> memberPremiumSpans;

    /**
     * Start date of the premium span
     */
    @JsonProperty(required = true)
    @Schema(description = "The start date of the premium span", example = "1/1/2021", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate startDate;

    /**
     * End date of the premium span
     */
    @JsonProperty(required = true)
    @Schema(description = "The end date of the premium span", example = "1/1/2021", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate endDate;

    /**
     * The CSR Variant associated with the premium span
     */
    @JsonProperty(required = true)
    @Schema(description = "The CSR Variant of the premium span", example = "01", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String csrVariant;

    /**
     * The total premium amount associated with the premium span
     */
    @JsonProperty(required = true)
    @Schema(description = "The total premium amount period of the premium span", example = "150.00", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private BigDecimal totalPremiumAmount;

    /**
     * The total responsible amount associated with the premium span
     */
    @JsonProperty(required = true)
    @Schema(description = "The total responsible amount period of the premium span", example = "150.00", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private BigDecimal totalResponsibleAmount;

    /**
     * The APTC amount associated with the premium span
     */
    @JsonProperty(required = false)
    @Schema(description = "The APTC amount period of the premium span", example = "150.00", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private BigDecimal aptcAmount;

    /**
     * The other pay amount associated with the premium span
     */
    @JsonProperty(required = false)
    @Schema(description = "The other pay amount period of the premium span", example = "150.00", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private BigDecimal otherPayAmount;

    /**
     * The CSR Amount associated with the premium span
     */
    @JsonProperty(required = false)
    @Schema(description = "The CSR amount period of the premium span", example = "150.00", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private BigDecimal csrAmount;

    /**
     * The date when the premium span was created
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the premium span was created", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime createdDate;

    /**
     * The date when the premium span was updated
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the premium span was updated", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime updatedDate;
}
