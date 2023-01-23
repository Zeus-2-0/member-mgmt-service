package com.brihaspathee.zeus.web.model;

import com.brihaspathee.zeus.dto.account.AccountList;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, January 2023
 * Time: 1:42 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestAccountMatchRequest {

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
     * The account match params that will be passed in a input
     */
    private AccountMatchParam accountMatchParam;

    /**
     * The list of accounts that are expected as results
     */
    private AccountList expectedAccountList;

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() {
        return "TestAccountMatchRequest{" +
                "exceptionExpected=" + exceptionExpected +
                ", exceptionCode='" + exceptionCode + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", httpStatusCode='" + httpStatusCode + '\'' +
                ", accountMatchParam=" + accountMatchParam +
                ", expectedAccountList=" + expectedAccountList +
                '}';
    }
}
