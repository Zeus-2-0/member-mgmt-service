package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.MemberDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 16, September 2022
 * Time: 7:40 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberService {

    /**
     * Get member by member code
     * @param memberCode
     * @return
     */
    MemberDto getMemberByCode(String memberCode);

    /**
     * Get all the members of an account
     * @param accountNumber
     * @return
     */
    Set<MemberDto> getMembersOfAccount(String accountNumber);

    /**
     * Create a member
     * @param memberDto
     * @return
     */
    MemberDto createMember(MemberDto memberDto);

    /**
     * Get member by social security number
     * @param socialSecurityNumber
     * @return
     */
    List<MemberDto> getMemberBySSN(String socialSecurityNumber);

    /**
     * Get the HOHs by SSN
     * @param socialSecurityNumber
     * @return
     */
    List<MemberDto> getHOHBySSN(String socialSecurityNumber);

    /**
     * Get member by first name, last name, gender type code and date of birth
     * @param firstName
     * @param lastName
     * @param genderTypeCode
     * @param dateOfBirth
     * @return
     */
    List<MemberDto> getMembersByNameAndDOB(String firstName, String lastName, String genderTypeCode, LocalDate dateOfBirth);

    /**
     * Save member updates or create new members as needed
     * @param accountDto
     */
    void saveMembers(AccountDto accountDto);


}
