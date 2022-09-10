package com.brihaspathee.zeus.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, March 2022
 * Time: 3:11 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    /**
     * The primary key of the account
     */
    @JsonProperty(required = false)
    @Schema(description = "Account SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private UUID accountSK;

    /**
     * The account number of the account
     */
    @JsonProperty(required = false)
    @Schema(description = "Account Number - Unique id that is assigned to the account", example = "DFEV323455DE5S3", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    private String accountNumber;

    /**
     * The line of business associated with the account
     */
    @JsonProperty(required = true)
    @Schema(description = "Line of business of the account", example = "HIX", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String lineOfBusinessTypeCode;

    /**
     * List of enrollment spans associated with the account
     */
    @JsonProperty(required = true)
    private Set<EnrollmentSpanDto> enrollmentSpans;

    /**
     * List of members associated with the account
     */
    @JsonProperty(required = true)
    private Set<MemberDto> members;

    /**
     * Account attributes associated with the account
     */
    @JsonProperty(required = false)
    private Set<AccountAttributeDto> accountAttributes;

    /**
     * The date when the account was created
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the account was created", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime createdDate;

    /**
     * The date when the account was updated
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the account was updated", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime updatedDate;

}
