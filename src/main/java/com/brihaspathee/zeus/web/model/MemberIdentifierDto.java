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
public class MemberIdentifierDto {

    /**
     * The primary key of the member identifier
     */
    @JsonProperty(required = false)
    @Schema(description = "Member Identifier SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private UUID memberIdentifierSK;

    /**
     * The primary key of the member
     */
    @JsonProperty(required = false)
    @Schema(description = "Member SK - This is a UUID value", example = "657cfd75-634e-49f1-9556-4d79f79848ec", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private UUID memberSK;

    /**
     * The identifier type code of the identifier
     */
    @JsonProperty(required = true)
    @Schema(description = "The identifier type code of the identifier", example = "SSN", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String identifierTypeCode;

    /**
     * The identifier of the member
     */
    @JsonProperty(required = true)
    @Schema(description = "The identifier of the member", example = "345-34-6434", required = true, accessMode = Schema.AccessMode.READ_WRITE)
    private String identifierValue;

    /**
     * The date when the identifier was created
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the identifier was created", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime createdDate;

    /**
     * The date when the identifier was updated
     */
    @JsonProperty(required = false)
    @Schema(description = "The date when the identifier was updated", example = "12/15/2021", required = false, accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime updatedDate;
}
