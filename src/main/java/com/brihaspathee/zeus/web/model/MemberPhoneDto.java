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
public class MemberPhoneDto {

    /**
     * The primary key of the member phone
     */
    @JsonProperty(required = false)
    @Schema(description = "Member Phone SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private UUID memberPhoneSK;

    /**
     * The primary key of the member
     */
    @JsonProperty(required = false)
    @Schema(description = "Member SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private UUID memberSK;

    /**
     * The phone type code of the phone
     */
    @JsonProperty(required = true)
    @Schema(description = "The phone type code of the phone", example = "MOBILE", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String phoneTypeCode;

    /**
     * The phone number of the member
     */
    @JsonProperty(required = true)
    @Schema(description = "The phone number of the member", example = "813-357-9150", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String phoneNumber;

    /**
     * The start date of the phone
     */
    @JsonProperty(required = true)
    @Schema(description = "The start date of the phone", example = "1/1/2021", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate startDate;

    /**
     * The end date of the phone
     */
    @JsonProperty(required = false)
    @Schema(description = "The end date of the phone", example = "12/31/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate endDate;

    /**
     * The date when the phone number was created
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the phone number was created", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime createdDate;

    /**
     * The date when the phone number was updated
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the phone number was updated", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime updatedDate;
}
