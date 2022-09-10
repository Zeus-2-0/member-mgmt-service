package com.brihaspathee.zeus.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, September 2022
 * Time: 7:11 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountAttributeDto {

    /**
     * The primary key of the account attribute
     */
    @JsonProperty(required = false)
    @Schema(description = "Account Attribute SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private UUID accountAttributeSK;

    /**
     * The primary key of the attribute
     */
    @JsonProperty(required = true)
    @Schema(description = "Attribute SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private UUID attributeSK;

    /**
     * The primary key of the account
     */
    @JsonProperty(required = false)
    @Schema(description = "Account SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private UUID accountSK;

    /**
     * The value of the attribute
     */
    @JsonProperty(required = true)
    @Schema(description = "The value of the attribute", example = "FFM-FL", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String attributeValue;

    /**
     * The date when the account attribute was created
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the account attribute was created", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime createdDate;

    /**
     * The date when the enrollment span was updated
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the account attribute was updated", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime updatedDate;
}
