package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.test.validator.AccountValidator;
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
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class AccountMatchAPIIntTest {

    /**
     * Object mapper to read the file and convert it to an object
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Rest template to call the api endpoint
     */
    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * The file that contains the test data
     */
    @Value("classpath:com/brihaspathee/zeus/integration/AccountMatchAPIIntTest.json")
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
     * The list of test requests
     */
    private List<TestAccountMatchRequest> requests = new ArrayList<>();

    /**
     * The account validation instance to validate the details of the account
     */
    private final AccountValidator accountValidator = new AccountValidator();

    /**
     * The setup method is executed before each test method is executed
     * @param testInfo
     * @throws IOException
     */
    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {

        // Read the file information and convert to test class object
        accountMatchRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestAccountMatchRequest>>() {});

        accountValidator.setTestServiceName("MEMBER-MGMT-SERVICE");

        // Build the test data for the test method that is to be executed
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(),this.accountMatchRequestTestClass);
        objectMapper.findAndRegisterModules();
    }

    /**
     * This method tests the get matched account end point
     * @param repetitionInfo
     */
    @RepeatedTest(4)
    @Order(1)
    void testGetMatchedAccounts(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the account match request for the repetition
        TestAccountMatchRequest testAccountMatchRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        log.info("Test account match request:{}", testAccountMatchRequest);
        // Get the expected account list from the test data
        AccountList expectedAccountList = testAccountMatchRequest.getExpectedAccountList();
        log.info("Account Match params present:{}", testAccountMatchRequest.getAccountMatchParam());
        AccountMatchParam accountMatchParam = testAccountMatchRequest.getAccountMatchParam();
        String exchangeSubscriberId = accountMatchParam.getExchangeSubscriberId();
        String stateTypeCode = accountMatchParam.getStateTypeCode();
        String accountNumber = accountMatchParam.getAccountNumber();
        String ssn = accountMatchParam.getSocialSecurityNumber();
        String firstName = accountMatchParam.getFirstName();
        String lastName = accountMatchParam.getLastName();
        String gender = accountMatchParam.getGenderTypeCode();
        String dob = "";
        if(accountMatchParam.getDateOfBirth()!=null){
            dob = accountMatchParam.getDateOfBirth().toString();
        }

        String uri = "";
        if(accountNumber != null){
            uri = "/api/v1/zeus/account/search?accountNumber="+accountNumber;
        }else{
            uri = "/api/v1/zeus/account/search?exchangeSubscriberId="+exchangeSubscriberId+
                    "&stateTypeCode="+stateTypeCode+
                    "&socialSecurityNumber="+ssn+
                    "&firstName="+firstName+
                    "&lastName="+lastName+
                    "&genderTypeCode="+gender+
                    "&dateOfBirth="+dob;
        }
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
            accountValidator.assertAccount(expectedAccountList, actualAccountList);
        }else{
            assertNull(actualAccountList.getAccountDtos());
        }


    }
}
