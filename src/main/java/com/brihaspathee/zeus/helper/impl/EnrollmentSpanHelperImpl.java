package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.domain.repository.EnrollmentSpanRepository;
import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanDto;
import com.brihaspathee.zeus.dto.account.MemberDto;
import com.brihaspathee.zeus.dto.account.MemberPremiumDto;
import com.brihaspathee.zeus.exception.EnrollmentSpanNotFoundException;
import com.brihaspathee.zeus.exception.MemberNotFoundException;
import com.brihaspathee.zeus.helper.interfaces.EnrollmentSpanHelper;
import com.brihaspathee.zeus.helper.interfaces.PremiumSpanHelper;
import com.brihaspathee.zeus.mapper.interfaces.EnrollmentSpanMapper;
import com.brihaspathee.zeus.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, September 2022
 * Time: 6:55 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 * Confluence: <a href="https://vbalaji.atlassian.net/wiki/spaces/ZEUS/pages/98926645/Enrollment+Span+Helper">Confluence</a>
 * Nuclino: <a href="https://share.nuclino.com/p/Enrollment-Span-Helper-T6W0El3FN0AxKRbjjoYcNm">Nuclino</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EnrollmentSpanHelperImpl implements EnrollmentSpanHelper {

    /**
     * Mapper to convert dto to entity and vice versa
     */
    private final EnrollmentSpanMapper enrollmentSpanMapper;

    /**
     * Repository to perform CRUD operations
     */
    private final EnrollmentSpanRepository enrollmentSpanRepository;

    /**
     * Premium span helper to create or update premium spans
     */
    private final PremiumSpanHelper premiumSpanHelper;

    /**
     * Member service instance to retrieve the member if not present in the payload
     */
    private final MemberService memberService;


    /**
     * Get enrollment spans that match exchange subscriber id and state type code
     * @param exchangeSubscriberId
     * @param stateTypeCode
     * @return
     * Nuclino: <a href="https://app.nuclino.com/t/b/9929faaa-47be-4f5a-a254-96644d3dd6d5#1fjRI702">Nuclino</a>
     * Confluence: <a href="https://vbalaji.atlassian.net/wiki/spaces/ZEUS/pages/98926645/Enrollment+Span+Helper#Get-Matching-Enrollment-Span">Confluence</a>
     */
    @Override
    public List<EnrollmentSpan> getMatchingEnrollmentSpan(String exchangeSubscriberId, String stateTypeCode) {
        List<EnrollmentSpan> enrollmentSpans = enrollmentSpanRepository
                .findEnrollmentSpansByExchangeSubscriberIdAndStateTypeCode(exchangeSubscriberId,
                        stateTypeCode);
        return enrollmentSpans;
    }

    /**
     * Update enrollment span status and paid through dates
     * @param enrollmentSpanDto
     */
    @Override
    public void updateEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto) {
        EnrollmentSpan enrollmentSpan = enrollmentSpanRepository
                .findEnrollmentSpanByEnrollmentSpanCode(enrollmentSpanDto.getEnrollmentSpanCode())
                .orElseThrow(() ->
                        new EnrollmentSpanNotFoundException(enrollmentSpanDto.getEnrollmentSpanCode()));
        LocalDate currentPTD = enrollmentSpan.getPaidThroughDate();
        String currentStatus = enrollmentSpan.getStatusTypeCode();
        log.info("Enrollment Span to be updated:{}", enrollmentSpanDto);
        log.info("Enrollment Span paid through date to be updated:{}", enrollmentSpanDto.getPaidThroughDate());
        // Check if either the paid through date or the current status is different from what is passed in the input
        if(enrollmentSpanDto.getPaidThroughDate() == null){
            enrollmentSpan.setPaidThroughDate(enrollmentSpanDto.getPaidThroughDate());
            enrollmentSpan.setClaimPaidThroughDate(enrollmentSpanDto.getPaidThroughDate());
            enrollmentSpan.setStatusTypeCode(enrollmentSpanDto.getStatusTypeCode());
            enrollmentSpan.setEffectuationDate(enrollmentSpanDto.getEffectuationDate());
        } else if(currentPTD == null || !enrollmentSpanDto.getPaidThroughDate().isEqual(currentPTD) ||
                !enrollmentSpanDto.getStatusTypeCode().equals(currentStatus)){
            enrollmentSpan.setPaidThroughDate(enrollmentSpanDto.getPaidThroughDate());
            enrollmentSpan.setClaimPaidThroughDate(enrollmentSpanDto.getPaidThroughDate());
            enrollmentSpan.setStatusTypeCode(enrollmentSpanDto.getStatusTypeCode());
            // If the enrollment span does not have an effectuation date but the update received has
            // and effectuation date then update the effectuation date
            if(enrollmentSpan.getEffectuationDate() == null &&
            enrollmentSpanDto.getEffectuationDate() !=null){
                enrollmentSpan.setEffectuationDate(enrollmentSpanDto.getEffectuationDate());
            }

        }
        enrollmentSpanRepository.save(enrollmentSpan);
    }

    /**
     * Save the enrollment spans associated with the account
     * Enrollment span will be updated if it is already present
     * Enrollment span will be created if it is not present
     * @param accountDto The account that contains the enrollment spans
     * Nuclino: <a href="https://app.nuclino.com/t/b/9929faaa-47be-4f5a-a254-96644d3dd6d5#5QdPT2dD">Nuclino</a>
     * Confluence: <a href="https://vbalaji.atlassian.net/wiki/spaces/ZEUS/pages/98926645/Enrollment+Span+Helper#Save-Enrollment-Span">Confluence</a>
     */
    @Override
    public void saveEnrollmentSpans(AccountDto accountDto) {
        if(accountDto.getEnrollmentSpans() != null && !accountDto.getEnrollmentSpans().isEmpty()){
            accountDto.getEnrollmentSpans().forEach(enrollmentSpanDto -> {
                if(enrollmentSpanDto.getEnrollmentSpanSK() == null){
                    // The enrollment span does not exist for the account
                    // it has to be created
                    createEnrollmentSpan(accountDto, enrollmentSpanDto);
                }else{
                    // Enrollment span exist for the account
                    if(enrollmentSpanDto.getChanged().get()){
                        enrollmentSpanDto.setAccountSK(accountDto.getAccountSK());
                        // enrollmentSpanDto.getPremiumSpans().forEach(premiumSpanDto -> premiumSpanDto.setEnrollmentSpanSK(enrollmentSpanDto.getEnrollmentSpanSK()));
                        // Enrollment span has changed hence it has to be updated
                        updateEnrollmentSpan(enrollmentSpanDto, accountDto);
                        updatePremiumSpans(enrollmentSpanDto, accountDto);
                    }else{
                        // enrollment span is not updated
                        // check if premium spans are updated
                        // this happens on a CHANGE transaction
                        updatePremiumSpans(enrollmentSpanDto, accountDto);

                    }
                }

                //enrollmentSpanHelper.updateEnrollmentSpan(enrollmentSpanDto, accountDto);
            });
        }
    }

    /**
     * Update an enrollment span
     * @param enrollmentSpanDto
     * @param accountDto
     */
    private void updateEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto,
                                      AccountDto accountDto) {
        EnrollmentSpan enrollmentSpan = enrollmentSpanMapper.enrollmentSpanDtoToEnrollmentSpan(enrollmentSpanDto);
        log.info("Enrollment span to be updated:{}", enrollmentSpan);
        enrollmentSpan = enrollmentSpanRepository.save(enrollmentSpan);
        log.info("Updated Enrollment span:{}", enrollmentSpan);
    }

    /**
     * Update the premium span
     * @param enrollmentSpanDto
     * @param accountDto
     */
    private void updatePremiumSpans(EnrollmentSpanDto enrollmentSpanDto, AccountDto accountDto){
        enrollmentSpanDto.getPremiumSpans().forEach(premiumSpanDto -> {
            premiumSpanDto.setEnrollmentSpanSK(enrollmentSpanDto.getEnrollmentSpanSK());
            // Check if the premium span already exists
            if(premiumSpanDto.getPremiumSpanSK() == null){
                // Premium span does not exist for the enrollment span
                // so create it
                // First set the members in the member premiums
                premiumSpanDto.getMemberPremiumSpans().forEach(memberPremiumDto -> {
                    if(memberPremiumDto.getMemberSK() == null){

                        populateMemberSK(memberPremiumDto, accountDto.getMembers());
                    }
                });
                UUID premiumSpanSK = premiumSpanHelper.createPremiumSpan(premiumSpanDto).getPremiumSpanSK();
                premiumSpanDto.setPremiumSpanSK(premiumSpanSK);
            }else{
                // Premium span exist check if it has to be updated
                if(premiumSpanDto.getChanged().get()){
                    // premium span has to be updated
                    premiumSpanHelper.updatePremiumSpan(premiumSpanDto);
                }
            }
        });
    }

    /**
     * Create an enrollment span
     * @param enrollmentSpanDto
     * @return
     */
    private EnrollmentSpanDto createEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto) {
        EnrollmentSpan enrollmentSpan = enrollmentSpanMapper.enrollmentSpanDtoToEnrollmentSpan(enrollmentSpanDto);
        enrollmentSpan = enrollmentSpanRepository.save(enrollmentSpan);
        log.info("Created Enrollment span:{}", enrollmentSpan);
        return enrollmentSpanMapper.enrollmentSpanToEnrollmentSpanDto(enrollmentSpan);
    }

    /**
     * Create a new enrollment span for the account
     * @param accountDto
     * @param enrollmentSpanDto
     */
    private void createEnrollmentSpan(AccountDto accountDto, EnrollmentSpanDto enrollmentSpanDto) {
        enrollmentSpanDto.setAccountSK(accountDto.getAccountSK());
        UUID enrollmentSpanSK = createEnrollmentSpan(enrollmentSpanDto).getEnrollmentSpanSK();
        enrollmentSpanDto.setEnrollmentSpanSK(
                enrollmentSpanSK);
        enrollmentSpanDto.getPremiumSpans().forEach(premiumSpanDto -> {
            premiumSpanDto.getMemberPremiumSpans().forEach(memberPremiumDto -> {
                if(memberPremiumDto.getMemberSK() == null){
                    log.info("Members in the account:{}", accountDto.getMembers());
                    populateMemberSK(memberPremiumDto, accountDto.getMembers());
                }
            });
            premiumSpanDto.setEnrollmentSpanSK(enrollmentSpanSK);
            UUID premiumSpanSK = premiumSpanHelper.createPremiumSpan(premiumSpanDto).getPremiumSpanSK();
            premiumSpanDto.setPremiumSpanSK(premiumSpanSK);
        });
    }

    /**
     * Populate the member surrogate key to the member premium objects
     * @param memberPremiumDto
     * @param memberDtos
     */
    private void populateMemberSK(MemberPremiumDto memberPremiumDto, Set<MemberDto> memberDtos){
        log.info("Inside populate member SK method");
        // Members in the member premium may not be present in the payload from APS unless they
        // are either newly created or updated
        String memberCode = memberPremiumDto.getMemberCode();
        MemberDto retrievedMember = null;
        if(memberDtos == null || memberDtos.isEmpty()){
            // So if it is not in the payload then get it from the database
            retrievedMember = retrieveMember(memberCode);
        }else{
            // if there are members in the payload
            // check if the member that is needed in the premium span is present
            // if not then retrieve it from DB
            retrievedMember = memberDtos.stream()
                    .filter(
                            memberDto ->
                                    memberDto.getMemberCode().equals(memberPremiumDto.getMemberCode()))
                    .findFirst()
                    .orElseGet(() -> retrieveMember(memberPremiumDto.getMemberCode()));
        }
        if(retrievedMember != null){
            memberPremiumDto.setMemberSK(retrievedMember.getMemberSK());
        }else{
            throw new MemberNotFoundException("Member with member code " + memberCode + " not found");
        }


    }

    /**
     * Get member by member code
     * @param memberCode
     * @return
     */
    private MemberDto retrieveMember(String memberCode){
        return memberService.getMemberByCode(memberCode);
    }
}
