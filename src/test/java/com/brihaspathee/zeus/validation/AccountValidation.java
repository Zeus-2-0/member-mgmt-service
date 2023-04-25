package com.brihaspathee.zeus.validation;

import com.brihaspathee.zeus.domain.entity.MemberPremium;
import com.brihaspathee.zeus.dto.account.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 04, April 2023
 * Time: 1:41 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.validation
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
public class AccountValidation {

    /**
     * Validate the details the account
     * @param expectedAccountList
     * @param actualAccountList
     */
    public void assertAccount(AccountList expectedAccountList,
                               AccountList actualAccountList){
        Set<AccountDto> expectedAccountDtos = expectedAccountList.getAccountDtos();
        Set<AccountDto> actualAccountDtos = actualAccountList.getAccountDtos();
        log.info("Ex Accounts:{}", expectedAccountDtos);
        log.info("Ac Account:{}", actualAccountDtos);
        expectedAccountDtos.stream().forEach( (expectedAccountDto -> {
            AccountDto actualAccount = actualAccountDtos.stream().filter(
                            (actualAccountDto) -> {
                                return expectedAccountDto.getAccountNumber().equals(actualAccountDto.getAccountNumber());
                            })
                    .findFirst().orElse(AccountDto.builder()
                            .accountSK(UUID.randomUUID())
                            .accountNumber("Random Account")
                            .build());
            assertAccountDetails(expectedAccountDto, actualAccount);
        }));
    }

    /**
     * Compare the account details
     * @param expectedAccountDto
     * @param actualAccountDto
     */
    public void assertAccountDetails(AccountDto expectedAccountDto, AccountDto actualAccountDto){
        log.info("Expected Account:{}", expectedAccountDto);
        log.info("Actual Account:{}", actualAccountDto);
        assertEquals(expectedAccountDto.getAccountNumber(), actualAccountDto.getAccountNumber());
        assertEnrollmentSpans(expectedAccountDto.getEnrollmentSpans(), actualAccountDto.getEnrollmentSpans());
        assertMemberDetails(expectedAccountDto.getMembers(), actualAccountDto.getMembers());
    }

    /**
     * Assert the enrollment spans
     * @param expectedEnrollmentSpans
     * @param actualEnrollmentSpans
     */
    private void assertEnrollmentSpans(Set<EnrollmentSpanDto> expectedEnrollmentSpans,
                                       Set<EnrollmentSpanDto> actualEnrollmentSpans){
        int expectedEnrollmentSpanSize = expectedEnrollmentSpans.size();
        int actualEnrollmentSpanSize = actualEnrollmentSpans.size();
        assertEquals(expectedEnrollmentSpanSize, actualEnrollmentSpanSize);
        if(expectedEnrollmentSpanSize == actualEnrollmentSpanSize){
            expectedEnrollmentSpans.stream().forEach(expectedEnrollmentSpanDto -> {
                EnrollmentSpanDto actualEnrollmentSpanDto = actualEnrollmentSpans.stream().filter(actualSpan ->
                                expectedEnrollmentSpanDto.getEnrollmentSpanCode().equals(actualSpan.getEnrollmentSpanCode()))
                        .findFirst().orElse(EnrollmentSpanDto.builder()
                                .enrollmentSpanCode("Random Enrollment Span")
                                .build());
                String expectedEnrollmentSpanCode = expectedEnrollmentSpanDto.getEnrollmentSpanCode();
                String actualEnrollmentSpanCode = actualEnrollmentSpanDto.getEnrollmentSpanCode();
                assertEquals(expectedEnrollmentSpanCode,
                        actualEnrollmentSpanCode);
                if(expectedEnrollmentSpanCode.equals(actualEnrollmentSpanCode)){
                    assertEquals(expectedEnrollmentSpanDto.getStateTypeCode(),
                            actualEnrollmentSpanDto.getStateTypeCode());
                    assertEquals(expectedEnrollmentSpanDto.getMarketplaceTypeCode(),
                            actualEnrollmentSpanDto.getMarketplaceTypeCode());
                    assertEquals(expectedEnrollmentSpanDto.getBusinessUnitTypeCode(),
                            actualEnrollmentSpanDto.getBusinessUnitTypeCode());
                    assertEquals(expectedEnrollmentSpanDto.getCoverageTypeCode(),
                            actualEnrollmentSpanDto.getCoverageTypeCode());
                    assertEquals(expectedEnrollmentSpanDto.getStartDate(),
                            actualEnrollmentSpanDto.getStartDate());
                    assertEquals(expectedEnrollmentSpanDto.getEndDate(),
                            actualEnrollmentSpanDto.getEndDate());
                    assertEquals(expectedEnrollmentSpanDto.getExchangeSubscriberId(),
                            actualEnrollmentSpanDto.getExchangeSubscriberId());
                    assertEquals(expectedEnrollmentSpanDto.getPlanId(),
                            actualEnrollmentSpanDto.getPlanId());
                    assertEquals(expectedEnrollmentSpanDto.getGroupPolicyId(),
                            actualEnrollmentSpanDto.getGroupPolicyId());
                    assertEquals(expectedEnrollmentSpanDto.getProductTypeCode(),
                            actualEnrollmentSpanDto.getProductTypeCode());
                    assertEquals(expectedEnrollmentSpanDto.getEffectuationDate(),
                            actualEnrollmentSpanDto.getEffectuationDate());
                    assertEquals(expectedEnrollmentSpanDto.getStatusTypeCode(),
                            actualEnrollmentSpanDto.getStatusTypeCode());
                    assertEquals(expectedEnrollmentSpanDto.getZtcn(),
                            actualEnrollmentSpanDto.getZtcn());
                    assertEquals(expectedEnrollmentSpanDto.isDelinqInd(),
                            actualEnrollmentSpanDto.isDelinqInd());
                    assertEquals(expectedEnrollmentSpanDto.getClaimPaidThroughDate(),
                            actualEnrollmentSpanDto.getClaimPaidThroughDate());
                    assertEquals(expectedEnrollmentSpanDto.getPaidThroughDate(),
                            actualEnrollmentSpanDto.getPaidThroughDate());
                    assertPremiumSpanDetails(expectedEnrollmentSpanDto.getPremiumSpans(),
                            actualEnrollmentSpanDto.getPremiumSpans());
                }
            });
        }
    }

    /**
     * Assert the details of the premium spans
     * @param expectedPremiumSpans
     * @param actualPremiumSpans
     */
    private void assertPremiumSpanDetails(Set<PremiumSpanDto> expectedPremiumSpans,
                                          Set<PremiumSpanDto> actualPremiumSpans){
        int expectedPremiumSpanSize = expectedPremiumSpans.size();
        int actualPremiumSpanSize = actualPremiumSpans.size();
        assertEquals(expectedPremiumSpanSize, actualPremiumSpanSize);
        if(expectedPremiumSpanSize == actualPremiumSpanSize){
            expectedPremiumSpans.stream().forEach(expectedPremiumSpanDto -> {
                PremiumSpanDto actualPremiumSpanDto = actualPremiumSpans.stream().filter(actualPremiumSpan ->
                        expectedPremiumSpanDto.getPremiumSpanCode().equals(actualPremiumSpan.getPremiumSpanCode()))
                            .findFirst().orElse(PremiumSpanDto.builder()
                                .premiumSpanCode("Random Premium Span")
                                .build());
                assertEquals(expectedPremiumSpanDto.getPremiumSpanCode(), actualPremiumSpanDto.getPremiumSpanCode());
                if(expectedPremiumSpanDto.getPremiumSpanCode().equals(actualPremiumSpanDto.getPremiumSpanCode())){
                    assertEquals(expectedPremiumSpanDto.getZtcn(), actualPremiumSpanDto.getZtcn());
                    assertEquals(expectedPremiumSpanDto.getStartDate(), actualPremiumSpanDto.getStartDate());
                    assertEquals(expectedPremiumSpanDto.getEndDate(), actualPremiumSpanDto.getEndDate());
                    log.info("Actual Premium Span Code:{}", actualPremiumSpanDto.getPremiumSpanCode());
                    log.info("Expected Premium Span Code:{}", expectedPremiumSpanDto.getPremiumSpanCode());
                    printComparedValues(expectedPremiumSpanDto.getStatusTypeCode(), actualPremiumSpanDto.getStatusTypeCode(), "Premium span status");
                    assertEquals(expectedPremiumSpanDto.getStatusTypeCode(), actualPremiumSpanDto.getStatusTypeCode());
                    assertEquals(expectedPremiumSpanDto.getCsrVariant(), actualPremiumSpanDto.getCsrVariant());
                    assertEquals(expectedPremiumSpanDto.getTotalPremiumAmount().longValue(),
                            actualPremiumSpanDto.getTotalPremiumAmount().longValue());
                    assertEquals(expectedPremiumSpanDto.getTotalResponsibleAmount().longValue(),
                            actualPremiumSpanDto.getTotalResponsibleAmount().longValue());
                    assertEquals(expectedPremiumSpanDto.getAptcAmount().longValue(),
                            actualPremiumSpanDto.getAptcAmount().longValue());
                    assertEquals(expectedPremiumSpanDto.getOtherPayAmount().longValue(),
                            actualPremiumSpanDto.getOtherPayAmount().longValue());
                    assertEquals(expectedPremiumSpanDto.getCsrAmount().longValue(),
                            actualPremiumSpanDto.getCsrAmount().longValue());
                }
            });
        }
    }

    private void assertMemberPremiums(Set<MemberPremiumDto> expectedMemberPremiums,
                                      Set<MemberPremiumDto> actualMemberPremiums){
        int expectedMemberPremiumSize = expectedMemberPremiums.size();
        int actualMemberPremiumSize = actualMemberPremiums.size();
        assertEquals(expectedMemberPremiumSize, actualMemberPremiumSize);
        if(expectedMemberPremiumSize == actualMemberPremiumSize){
            expectedMemberPremiums.stream().forEach(expectedMemberPremiumDto -> {
                MemberPremiumDto actualMemberPremiumDto = actualMemberPremiums.stream().filter(actualMemberPremium ->
                                expectedMemberPremiumDto.getMemberCode().equals(actualMemberPremium.getMemberCode()))
                        .findFirst().orElse(MemberPremiumDto.builder()
                                .memberCode("Random Member Code")
                                .build());
                assertEquals(expectedMemberPremiumDto.getMemberCode(), actualMemberPremiumDto.getMemberCode());
                if(expectedMemberPremiumDto.getMemberCode().equals(actualMemberPremiumDto.getMemberCode())){
                    assertEquals(expectedMemberPremiumDto.getExchangeMemberId(),
                            actualMemberPremiumDto.getExchangeMemberId());
                    assertEquals(expectedMemberPremiumDto.getIndividualPremiumAmount().longValue(),
                            actualMemberPremiumDto.getIndividualPremiumAmount().longValue());
                }
            });
        }
    }

    /**
     * Asser the details of the members
     * @param expectedMembers
     * @param actualMembers
     */
    public void assertMemberDetails(Set<MemberDto> expectedMembers,
                                     Set<MemberDto> actualMembers){
        int expectedMemberSize = expectedMembers.size();
        int actualMemberSize = actualMembers.size();
        assertEquals(expectedMemberSize, actualMemberSize);
        if(expectedMemberSize == actualMemberSize){
            expectedMembers.stream().forEach(expectedMemberDto -> {
                MemberDto actualMemberDto = actualMembers.stream().filter(actualMemDto ->
                    expectedMemberDto.getMemberCode().equals(actualMemDto.getMemberCode()))
                        .findFirst().orElse(MemberDto.builder()
                                .memberCode("Random Member Code")
                                .build());
                assertEquals(expectedMemberDto.getMemberCode(), actualMemberDto.getMemberCode());
                if(expectedMemberDto.getMemberCode().equals(actualMemberDto.getMemberCode())){
                    assertEquals(expectedMemberDto.getFirstName(), actualMemberDto.getFirstName());
                    assertEquals(expectedMemberDto.getMiddleName(), actualMemberDto.getMiddleName());
                    assertEquals(expectedMemberDto.getLastName(), actualMemberDto.getLastName());
                    assertEquals(expectedMemberDto.getDateOfBirth(), actualMemberDto.getDateOfBirth());
                    assertEquals(expectedMemberDto.getRelationshipTypeCode(), actualMemberDto.getRelationshipTypeCode());
                    assertEquals(expectedMemberDto.getGenderTypeCode(), actualMemberDto.getGenderTypeCode());
                    assertEquals(expectedMemberDto.getHeight(), expectedMemberDto.getHeight());
                    assertEquals(expectedMemberDto.getWeight(), expectedMemberDto.getWeight());
                    assertMemberAddresses(expectedMemberDto.getMemberAddresses(), actualMemberDto.getMemberAddresses());
                }
            });
        }
    }

    /**
     * Assert the details of the member address
     * @param expectedMemberAddresses
     * @param actualMemberAddresses
     */
    public void assertMemberAddresses(Set<MemberAddressDto> expectedMemberAddresses,
                                      Set<MemberAddressDto> actualMemberAddresses){
        int expectedMemberAddressSize = expectedMemberAddresses.size();
        int actualMemberAddressSize = actualMemberAddresses.size();
        assertEquals(expectedMemberAddressSize, actualMemberAddressSize);
        if(expectedMemberAddressSize == actualMemberAddressSize) {
            expectedMemberAddresses.stream().forEach(expectedMemberAddressDto -> {
                MemberAddressDto actualMemberAddressDto = actualMemberAddresses.stream().filter(actualMemAddrDto ->
                                expectedMemberAddressDto.getMemberAddressCode().equals(actualMemAddrDto.getMemberAddressCode()))
                        .findFirst().orElse(MemberAddressDto.builder()
                                .memberAddressCode("Random Member Address Code")
                                .build());
                assertEquals(expectedMemberAddressDto.getMemberAddressCode(), actualMemberAddressDto.getMemberAddressCode());
                if (expectedMemberAddressDto.getMemberAddressCode().equals(actualMemberAddressDto.getMemberAddressCode())) {
                    assertEquals(expectedMemberAddressDto.getAddressTypeCode(), actualMemberAddressDto.getAddressTypeCode());
                    assertEquals(expectedMemberAddressDto.getAddressLine1(), actualMemberAddressDto.getAddressLine1());
                    assertEquals(expectedMemberAddressDto.getAddressLine2(), actualMemberAddressDto.getAddressLine2());
                    assertEquals(expectedMemberAddressDto.getCity(), actualMemberAddressDto.getCity());
                    assertEquals(expectedMemberAddressDto.getStateTypeCode(), actualMemberAddressDto.getStateTypeCode());
                    assertEquals(expectedMemberAddressDto.getZipCode(), actualMemberAddressDto.getZipCode());
                }
            });
        }
    }

    /**
     * Method to print data that is being compared
     * @param actualString
     * @param expectedString
     * @param comparedData
     */
    private void printComparedValues(String actualString, String expectedString, String comparedData){
        log.info("Data being compared:{}", comparedData);
        log.info("Actual " + comparedData + ":" + actualString);
        log.info("Expected " + comparedData + ":" + expectedString);
    }
}
