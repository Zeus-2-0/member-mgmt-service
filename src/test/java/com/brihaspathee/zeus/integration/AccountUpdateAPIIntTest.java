package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.test.validator.AccountValidation;
import com.brihaspathee.zeus.web.model.TestAccountUpdateRequest;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, March 2023
 * Time: 5:45 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.integration
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountUpdateAPIIntTest {

    /**
     * Object mapper to read the file and convert it to an object
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Rest template to call the api endpoint
     */
    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * Account service instance to get the updated account
     */
    @Autowired
    private AccountService accountService;

    /**
     * The file that contains the test data
     */
    @Value("classpath:com/brihaspathee/zeus/integration/AccountUpdateAPIIntTest.json")
    Resource resourceFile;

    /**
     * Test class with the account update request information
     */
    private TestClass<TestAccountUpdateRequest> accountUpdateRequestTestClass;

    /**
     * The instance of the class that helps to build the data
     */
    @Autowired
    private BuildTestData<TestAccountUpdateRequest> buildTestData;

    /**
     * The list of test requests
     */
    private List<TestAccountUpdateRequest> requests = new ArrayList<>();

    /**
     * The account validation instance to validate the details of the account
     */
    private AccountValidation accountValidation = new AccountValidation();

    /**
     * The setup method is executed before each test method is executed
     * @param testInfo
     * @throws IOException
     */
    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {

        // Read the file information and convert to test class object
        accountUpdateRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestAccountUpdateRequest>>() {});

        accountValidation.setTestServiceName("MEMBER-MGMT-SERVICE");

        // Build the test data for the test method that is to be executed
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(),this.accountUpdateRequestTestClass);
    }

    /**
     * This method tests the get matched account end point
     * @param repetitionInfo
     */
    @RepeatedTest(9)
    @Order(1)
    void testUpdateAccount(RepetitionInfo repetitionInfo) throws JsonProcessingException {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the account update request for the repetition
        TestAccountUpdateRequest testAccountUpdateRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        log.info("Test account update request:{}", testAccountUpdateRequest);
        // Get the account dto to be updated from the test data
        AccountDto inputAccountDto = testAccountUpdateRequest.getInputAccountDto();
        // Get the account dto that is expected after the update is made
        AccountDto expectedAccountUpdateDto = testAccountUpdateRequest.getExpectedAccountUpdateDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountDto> httpEntity = new HttpEntity<>(inputAccountDto, headers);
        String uri = "/api/v1/zeus/account/update";
        // Call the API Endpoint to process the transaction
        ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                .postForEntity(uri,httpEntity, ZeusApiResponse.class);
        // Get the updated account dto object
        AccountDto updatedAccountDto = getUpdatedAccount(inputAccountDto.getAccountNumber());
        String accountAsString = objectMapper.writeValueAsString(updatedAccountDto);
        log.info("Updated Account Dto:{}", accountAsString);
        accountValidation.assertAccount(expectedAccountUpdateDto, updatedAccountDto);
    }

    /**
     * This method tests the get matched account end point
     * @param repetitionInfo
     */
    @RepeatedTest(3)
    @Order(1)
    void testCreateAccount(RepetitionInfo repetitionInfo) throws JsonProcessingException {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the account create request for the repetition
        TestAccountUpdateRequest testAccountUpdateRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        log.info("Test account create request:{}", testAccountUpdateRequest);
        // Get the account dto to be created from the test data
        AccountDto inputAccountDto = testAccountUpdateRequest.getInputAccountDto();
        // Get the account dto that is expected after the account creation is made
        AccountDto expectedAccountUpdateDto = testAccountUpdateRequest.getExpectedAccountUpdateDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountDto> httpEntity = new HttpEntity<>(inputAccountDto, headers);
        String uri = "/api/v1/zeus/account";
        // Call the API Endpoint to process the transaction
        ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                .postForEntity(uri,httpEntity, ZeusApiResponse.class);
        // Get the updated account dto object
        AccountDto updatedAccountDto = getUpdatedAccount(inputAccountDto.getAccountNumber());
        String accountAsString = objectMapper.writeValueAsString(updatedAccountDto);
        log.info("Created Account Dto:{}", accountAsString);
        accountValidation.assertAccount(expectedAccountUpdateDto, updatedAccountDto);
    }

    /**
     * Get the account that was updated
     * @param accountNumber
     * @return
     */
    private AccountDto getUpdatedAccount(String accountNumber){
        String uri = "/api/v1/zeus/account/search?accountNumber="+accountNumber;
        ResponseEntity<ZeusApiResponse> updatedAccountResponseEntity  =
                testRestTemplate.getForEntity(
                        uri,
                        ZeusApiResponse.class);
        // Get the response body from the response
        ZeusApiResponse updatedAccountResponse = updatedAccountResponseEntity.getBody();
        // get the list of accounts that matched
        AccountList accountList =
                objectMapper.convertValue(updatedAccountResponse.getResponse(), AccountList.class);
        AccountDto accountDto = accountList.getAccountDtos().stream().findFirst().get();
        return accountDto;
    }
}
