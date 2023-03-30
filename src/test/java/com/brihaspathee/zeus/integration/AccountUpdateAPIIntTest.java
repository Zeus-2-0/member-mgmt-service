package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.web.model.AccountMatchParam;
import com.brihaspathee.zeus.web.model.TestAccountMatchRequest;
import com.brihaspathee.zeus.web.model.TestAccountUpdateRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;

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
     * The setup method is executed before each test method is executed
     * @param testInfo
     * @throws IOException
     */
    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {

        // Read the file information and convert to test class object
        accountUpdateRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestAccountUpdateRequest>>() {});

        // Build the test data for the test method that is to be executed
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(),this.accountUpdateRequestTestClass);
    }

    /**
     * This method tests the get matched account end point
     * @param repetitionInfo
     */
    @RepeatedTest(1)
    @Order(1)
    void testCreateAccount(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the account update request for the repetition
        TestAccountUpdateRequest testAccountUpdateRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        log.info("Test account update request:{}", testAccountUpdateRequest);
        // Get the account dto to be updated from the test data
        AccountDto inputAccountDto = testAccountUpdateRequest.getInputAccountDto();
        // Get the account dto that is expected after the update is made
        AccountDto expectedAccountUpdateDto = testAccountUpdateRequest.getExpectedAccountUpdateDto();
    }
}
