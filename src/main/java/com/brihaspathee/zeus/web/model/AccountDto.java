package com.brihaspathee.zeus.web.model;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, March 2022
 * Time: 3:11 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String accountSK;
    private String accountId;

    @Override
    public String toString() {
        return "AccountDto{" +
                "accountSK='" + accountSK + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
