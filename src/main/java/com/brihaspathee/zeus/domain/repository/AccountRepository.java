package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, April 2022
 * Time: 4:34 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    /**
     * Find the account by number
     * @param accountNumber
     * @return
     */
    Optional<Account> findAccountsByAccountNumber(String accountNumber);
}
