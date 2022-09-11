package com.brihaspathee.zeus.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, September 2022
 * Time: 10:11 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddressDto {

    /**
     * The primary key of the member address
     */
    @JsonProperty(required = false)
    @Schema(description = "Member Address SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private UUID memberAddressSK;

    /**
     * The primary key of the member
     */
    @JsonProperty(required = false)
    @Schema(description = "Member SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private UUID memberSK;

    /**
     * The type of the address
     */
    @JsonProperty(required = true)
    @Schema(description = "The type of the address", example = "Residential", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String addressTypeCode;

    /**
     * The address line 1 of the address
     */
    @JsonProperty(required = true)
    @Schema(description = "The address line 1 of the address", example = "123 Main Street", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String addressLine1;

    /**
     * The address line 2 of the address
     */
    @JsonProperty(required = false)
    @Schema(description = "The address line 2 of the address", example = "Apt 301", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String addressLine2;

    /**
     * The city of the address
     */
    @JsonProperty(required = true)
    @Schema(description = "The city of the address", example = "Tampa", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String city;

    /**
     * The state of the address
     */
    @JsonProperty(required = true)
    @Schema(description = "The state of the address", example = "FL", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String stateTypeCode;

    /**
     * The zip code of the address
     */
    @JsonProperty(required = true)
    @Schema(description = "The zipcode of the address", example = "33556", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String zipCode;

    /**
     * The start date of the address
     */
    @JsonProperty(required = true)
    @Schema(description = "The start date of the address", example = "1/1/2021", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate startDate;

    /**
     * The end date of the address
     */
    @JsonProperty(required = false)
    @Schema(description = "The end date of the address", example = "12/31/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate endDate;

    /**
     * The date when the address was created
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the address was created", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime createdDate;

    /**
     * The date when the address was updated
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the address was updated", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime updatedDate;
}
