package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.security.model.AuthorityDto;
import com.brihaspathee.zeus.security.model.AuthorityList;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.web.model.AccountMatchParam;
import com.brihaspathee.zeus.web.model.TestAccountMatchRequest;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, January 2023
 * Time: 1:39 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.integration
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountAPIIntTest {

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
     * The file that contains the test data
     */
    @Value("classpath:com/brihaspathee/zeus/integration/AccountAPIIntTest.json")
    Resource resourceFile;

    /**
     * Test class with the account match request information
     */
    private TestClass<TestAccountMatchRequest> accountMatchRequestTestClass;

    /**
     * The instance of the class that helps to build the data
     */
    @Autowired
    private BuildTestData<TestAccountMatchRequest> buildTestData;

    /**
     * The list of test user requests
     */
    private List<TestAccountMatchRequest> requests = new ArrayList<>();

    /**
     * The setup method is executed before each test method is executed
     * @param testInfo
     * @throws IOException
     */
    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {

        // Read the file information and convert to test class object
        accountMatchRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestAccountMatchRequest>>() {});

        // Build the test data for the test method that is to be executed
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(),this.accountMatchRequestTestClass);
    }

    /**
     * This method tests the get matched account end point
     * @param repetitionInfo
     */
    @RepeatedTest(3)
    @Order(1)
    void testGetMatchedAccounts(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAccountMatchRequest testAccountMatchRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        // validateAuthorityRequest(testAccountMatchRequest);
        log.info("Test authority request:{}", testAccountMatchRequest);
        // Get the expected account list from the test data
        AccountList expectedAccountList = testAccountMatchRequest.getExpectedAccountList();
        log.info("Account Match params present:{}", testAccountMatchRequest.getAccountMatchParam());
        AccountMatchParam accountMatchParam = testAccountMatchRequest.getAccountMatchParam();
        String exchangeSubscriberId = accountMatchParam.getExchangeSubscriberId();
        String stateTypeCode = accountMatchParam.getStateTypeCode();
        String uri = "/api/v1/zeus/account/search?exchangeSubscriberId="+exchangeSubscriberId+"&stateTypeCode="+stateTypeCode;
        ResponseEntity<ZeusApiResponse> responseEntity  =
                testRestTemplate.getForEntity(
                        uri,
                        ZeusApiResponse.class);
        // Get the response body from the response
        ZeusApiResponse actualResponse = responseEntity.getBody();
        // get the list of accounts that matched
        AccountList actualAccountList =
                objectMapper.convertValue(actualResponse.getResponse(), AccountList.class);
        log.info("Account List:{}", actualAccountList);
        if(expectedAccountList.getAccountDtos()!=null && expectedAccountList.getAccountDtos().size() > 0){
            assertEquals(expectedAccountList.getAccountDtos().size(), actualAccountList.getAccountDtos().size());
            assertAccountList(expectedAccountList, actualAccountList);
        }else{
            assertNull(actualAccountList.getAccountDtos());
        }


    }

    /**
     * Assert the details of the accounts
     * @param expectedAccountList
     * @param actualAccountList
     */
    private void assertAccountList(AccountList expectedAccountList, AccountList actualAccountList){
        Set<AccountDto> expectedAccountDtos = expectedAccountList.getAccountDtos();
        Set<AccountDto> actualAccountDtos = actualAccountList.getAccountDtos();
        log.info("Ex Accounts:{}", expectedAccountDtos);
        log.info("Ac Account:{}", actualAccountDtos);
        expectedAccountDtos.stream().forEach( (expectedAccountDto -> {
            AccountDto actualAccount = actualAccountDtos.stream().filter(
                            (actualAccountDto) -> {
                                return expectedAccountDto.getAccountSK().equals(actualAccountDto.getAccountSK());
                            })
                    .findFirst().orElse(AccountDto.builder()
                            .accountSK(UUID.randomUUID())
                            .accountNumber("Random Account")
                            .build());
            assetAccountDetails(expectedAccountDto, actualAccount);
        }));
    }

    /**
     * Compare the account details
     * @param expectedAccountDto
     * @param actualAccountDto
     */
    private void assetAccountDetails(AccountDto expectedAccountDto, AccountDto actualAccountDto){
        log.info("Expected Account:{}", expectedAccountDto);
        log.info("Actual Account:{}", actualAccountDto);
        assertEquals(expectedAccountDto.getAccountNumber(), actualAccountDto.getAccountNumber());
    }
}
