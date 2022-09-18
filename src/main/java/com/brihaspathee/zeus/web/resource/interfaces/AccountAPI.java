package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.model.AccountList;
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

import javax.validation.Valid;

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
    ResponseEntity<ZeusApiResponse<AccountDto>> getAccountByNumber(@PathVariable("accountNumber") String accountNumber);

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
}
