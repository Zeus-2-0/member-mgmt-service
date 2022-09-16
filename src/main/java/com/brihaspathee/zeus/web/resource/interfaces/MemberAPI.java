package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.model.AccountList;
import com.brihaspathee.zeus.web.model.MemberDto;
import com.brihaspathee.zeus.web.model.MemberList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 16, September 2022
 * Time: 7:04 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/member")
@Validated
public interface MemberAPI {

    /**
     * Get the member related to the member code that is passed in as input
     * @param memberCode
     * @return Returns the member object
     */
    @Operation(
            operationId = "Get Member by member code",
            method = "GET",
            description = "Get the details of the member by their code",
            tags = {"member"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of the member",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MemberDto.class))
                            }
                    )
            }
    )
    @GetMapping("/{memberCode}")
    ResponseEntity<ZeusApiResponse<MemberDto>> getMemberByCode(@PathVariable("memberCode") String memberCode);


    /**
     * Get all members of an account
     * @param accountNumber the account number of the account
     * @return The list of members
     */
    @Operation(
            operationId = "Get members of an account",
            method = "GET",
            description = "Get the details of all the members in an account",
            tags = {"member"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of all the members in an account",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MemberList.class))
                            }
                    )
            }
    )
    @GetMapping("/account/{accountNumber}")
    ResponseEntity<ZeusApiResponse<MemberList>> getAllMembersOfAccount(@PathVariable String accountNumber);
}
