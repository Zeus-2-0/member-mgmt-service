package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Account;
import com.brihaspathee.zeus.mapper.interfaces.AccountAttributeMapper;
import com.brihaspathee.zeus.mapper.interfaces.AccountMapper;
import com.brihaspathee.zeus.mapper.interfaces.EnrollmentSpanMapper;
import com.brihaspathee.zeus.mapper.interfaces.MemberMapper;
import com.brihaspathee.zeus.web.model.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, April 2022
 * Time: 4:25 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AccountMapperImpl implements AccountMapper {

    /**
     * Member Mapper to map the member entity
     */
    private final MemberMapper memberMapper;

    /**
     * Enrollment span mapper to map the enrollment span entity
     */
    private final EnrollmentSpanMapper enrollmentSpanMapper;

    /**
     * Account attribute mapper to map the account attribute entity
     */
    private final AccountAttributeMapper attributeMapper;

    /**
     * Convert account dto to account entity
     * @param accountDto
     * @return
     */
    @Override
    public Account accountDtoToAccount(AccountDto accountDto) {
        if(accountDto == null){
            return null;
        }
        Account account = Account.builder()
                .accountSK(accountDto.getAccountSK())
                .accountNumber(accountDto.getAccountNumber())
                .lineOfBusinessTypeCode(accountDto.getLineOfBusinessTypeCode())
                .members(memberMapper.memberDtosToMembers(accountDto.getMembers()))
                .enrollmentSpans(enrollmentSpanMapper.enrollmentSpanDtosToEnrollmentSpans(accountDto.getEnrollmentSpans()))
                .accountAttributes(attributeMapper.accountAttributeDtosToAccountAttributes(accountDto.getAccountAttributes()))
                .createdDate(accountDto.getCreatedDate())
                .updatedDate(accountDto.getUpdatedDate())
                .build();
        return account;
    }

    /**
     * Convert account entity to account dto
     * @param account
     * @return
     */
    @Override
    public AccountDto accountToAccountDto(Account account) {
        if(account == null){
            return null;
        }
        AccountDto accountDto = AccountDto.builder()
                .accountSK(account.getAccountSK())
                .accountNumber(account.getAccountNumber())
                .lineOfBusinessTypeCode(account.getLineOfBusinessTypeCode())
                .members(memberMapper.membersToMemberDtos(account.getMembers()))
                .enrollmentSpans(enrollmentSpanMapper.enrollmentSpansToEnrollmentSpanDtos(account.getEnrollmentSpans()))
                .accountAttributes(attributeMapper.accountAttributesToAccountAttributeDtos(account.getAccountAttributes()))
                .createdDate(account.getCreatedDate())
                .updatedDate(account.getUpdatedDate())
                .build();
        return accountDto;
    }

    /**
     * Convert account dtos to account entities
     * @param accountDtos
     * @return
     */
    @Override
    public Set<Account> accountDtosToAccount(Set<AccountDto> accountDtos) {
        return accountDtos.stream().map(this::accountDtoToAccount).collect(Collectors.toSet());
    }

    /**
     * Convert account entities to account dtos
     * @param accounts
     * @return
     */
    @Override
    public Set<AccountDto> accountToAccountDtos(Set<Account> accounts) {
        return accounts.stream().map(this::accountToAccountDto).collect(Collectors.toSet());
    }
}
