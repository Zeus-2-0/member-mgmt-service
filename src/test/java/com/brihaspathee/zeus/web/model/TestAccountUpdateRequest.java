package com.brihaspathee.zeus.web.model;

import com.brihaspathee.zeus.dto.account.AccountDto;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, March 2023
 * Time: 5:47 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestAccountUpdateRequest {

    /**
     * Identifies if an exception is expected
     */
    private boolean exceptionExpected;

    /**
     * The exception code when an exception is expected
     */
    private String exceptionCode;

    /**
     * The exception message when an exception is expected
     */
    private String exceptionMessage;

    /**
     * The http status code expected
     */
    private String httpStatusCode;

    /**
     * The account dto that is provided as input
     */
    private AccountDto inputAccountDto;

    /**
     * The account dto that is expected after the update is made
     */
    private AccountDto expectedAccountUpdateDto;

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() {
        return "TestAccountUpdateRequest{" +
                "exceptionExpected=" + exceptionExpected +
                ", exceptionCode='" + exceptionCode + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", httpStatusCode='" + httpStatusCode + '\'' +
                ", inputAccountDto=" + inputAccountDto +
                ", expectedAccountUpdate=" + expectedAccountUpdateDto +
                '}';
    }
}
