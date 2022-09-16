package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 01, July 2022
 * Time: 7:15 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    /**
     * Get the list of all the members associated with the account
     * @param account
     * @return
     */
    List<Member> findMembersByAccount(Account account);

    /**
     * Get the member associated with the member code
     * @param memberCode
     * @return
     */
    Optional<Member> findMemberByMemberCode(String memberCode);
}
