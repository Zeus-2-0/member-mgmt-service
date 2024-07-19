package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanDto;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanList;
import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.web.model.AccountMatchParam;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, March 2022
 * Time: 3:10 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/account")
@Validated
public interface AccountAPI {

    /**
     * Get the account related to the account number that is passed in as input
     * @param accountNumber
     * @return
     */
    @Operation(
            operationId = "Get Account by account number",
            method = "GET",
            description = "Get the details of the account by its number",
            tags = {"account"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of the account",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountDto.class))
                            }
                    )
            }
    )
    @GetMapping("/{accountNumber}")
    ResponseEntity<ZeusApiResponse<AccountList>> getAccountByNumber(@PathVariable("accountNumber") String accountNumber);

    /**
     * Get all the accounts in the system
     * @return
     */
    @Operation(
            operationId = "Get all accounts",
            method = "GET",
            description = "Get the details of all the accounts",
            tags = {"account"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of all the account",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountList.class))
                            }
                    )
            }
    )
    @GetMapping
    ResponseEntity<ZeusApiResponse<AccountList>> getAllAccounts();

    /**
     * Create a new account
     * @param accountDto
     * @return
     */
    @Operation(
            operationId = "Create a new account",
            method = "POST",
            description = "Create a new account",
            tags = {"account"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created the account",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    })
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZeusApiResponse<AccountDto>> createAccount(@RequestBody @Valid AccountDto accountDto) throws JsonProcessingException;

    /**
     * Update an existing account
     * @param accountDto
     * @return
     */
    @Operation(
            operationId = "Update an existing account",
            method = "POST",
            description = "Update an existing account",
            tags = {"account"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully update the account",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    })
    })
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZeusApiResponse<AccountDto>> updateAccount(@RequestBody @Valid AccountDto accountDto) throws JsonProcessingException;

    /**
     * Get matching enrollment spans for the plan id and group policy id
     * @param accountNumber
     * @param planId
     * @param groupPolicyId
     * @param startDate
     * @return
     */
    @Operation(
            operationId = "Get Enrollment spans by plan id and group policy id",
            method = "GET",
            description = "Get the Enrollment span that match plan id and group policy id",
            tags = {"enrollment-spans"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of the enrollment span",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountDto.class))
                            }
                    )
            }
    )
    @GetMapping("/match/plan/{accountNumber}/{planId}/{groupPolicyId}/{startDate}")
    ResponseEntity<ZeusApiResponse<EnrollmentSpanDto>> matchEnrollmentSpanByPlanAndGroupPolicyId(@PathVariable String accountNumber,
                                                                                                 @PathVariable String planId,
                                                                                                 @PathVariable String groupPolicyId,
                                                                                                 @PathVariable LocalDate startDate);

    /**
     * Get enrollment span that is prior to the start date provided in the input
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Operation(
            operationId = "Get Enrollment spans prior to the date provided",
            method = "GET",
            description = "Get Enrollment spans prior to the date provided",
            tags = {"enrollment-spans"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of the enrollment span",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountDto.class))
                            }
                    )
            }
    )
    @GetMapping("/prior/{accountNumber}/{startDate}/{matchCancelSpans}")
    ResponseEntity<ZeusApiResponse<EnrollmentSpanDto>> getPriorEnrollmentSpan(@PathVariable String accountNumber,
                                                                              @PathVariable LocalDate startDate,
                                                                              @PathVariable boolean matchCancelSpans);

    /**
     * Get enrollment span that matches by the date
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Operation(
            operationId = "Get Enrollment spans that match the start date provided",
            method = "GET",
            description = "Get Enrollment spans that match the start date provided",
            tags = {"enrollment-spans"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of the enrollment span",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountDto.class))
                            }
                    )
            }
    )
    @GetMapping("/match/date/{accountNumber}/{startDate}/{matchCancelSpans}")
    ResponseEntity<ZeusApiResponse<EnrollmentSpanList>> matchEnrollmentSpanByDate(@PathVariable String accountNumber,
                                                                                  @PathVariable LocalDate startDate,
                                                                                  @PathVariable boolean matchCancelSpans);

    /**
     * Get enrollment spans by the date or get the prior enrollment span
     * @param accountNumber
     * @param startDate
     * @param matchCancelSpans
     * @return
     */
    @Operation(
            operationId = "Get Enrollment spans by date or get prior enrollment span",
            method = "GET",
            description = "Get Enrollment spans by date or get prior enrollment span",
            tags = {"enrollment-spans"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of the enrollment span",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountDto.class))
                            }
                    )
            }
    )
    @GetMapping("/match/prior/{accountNumber}/{startDate}/{matchCancelSpans}")
    ResponseEntity<ZeusApiResponse<EnrollmentSpanList>> matchOrGetPriorEnrollmentSpan(@PathVariable String accountNumber,
                                                                                      @PathVariable LocalDate startDate,
                                                                                      @PathVariable boolean matchCancelSpans);

    /**
     * Match accounts by the search parameters
     * @param accountMatchParam
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get accounts matching the searching parameters",
            tags = {"account"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved matching accounts",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = AccountList.class))
                    })
    })
    @GetMapping("/search")
    ResponseEntity<ZeusApiResponse<AccountList>> getMatchingAccounts(
            AccountMatchParam accountMatchParam);

    /**
     * Get the accounts where the HOH of the account has the SSN passed in the input
     * @param ssn
     * @return
     */
    @Operation(
            operationId = "Get Accounts by ssn",
            method = "GET",
            description = "Get the details of the accounts by HOH's SSN",
            tags = {"account"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the details of the accounts",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountList.class))
                            }
                    )
            }
    )
    @GetMapping("/ssn/{ssn}")
    ResponseEntity<ZeusApiResponse<AccountList>> getAccountBySSN(@PathVariable("ssn") String ssn);

    /**
     * Clean up the entire db
     * @return
     */
    @Operation(
            operationId = "Delete all data",
            method = "DELETE",
            description = "Delete all data",
            tags = {"account"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Data deleted successfully",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ZeusApiResponse.class))
                    })
    })
    @DeleteMapping("/delete")
    ResponseEntity<ZeusApiResponse<String>> cleanUp();

    /**
     * Update the paid through date and status of an enrollment span
     * @param enrollmentSpanDto - The enrollment span that needs to be updated
     * @return
     */
    @Operation(
            operationId = "Update the paid through date and status of an enrollment span",
            method = "POST",
            description = "Update the paid through date and status of an enrollment span",
            tags = {"account"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully update the enrollment span",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = AccountDto.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    })
    })
    @PostMapping(path = "/enrollmentSpan/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZeusApiResponse<String>> updatePaidThroughDate(@RequestBody @Valid EnrollmentSpanDto enrollmentSpanDto) throws JsonProcessingException;

}
