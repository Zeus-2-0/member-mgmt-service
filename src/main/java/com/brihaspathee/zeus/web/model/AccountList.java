package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 15, September 2022
 * Time: 6:58 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountList {

    /**
     * List of all the account dtos
     */
    private Set<AccountDto> accountDtos;
}
