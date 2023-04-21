package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.domain.repository.EnrollmentSpanRepository;
import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.EnrollmentSpanDto;
import com.brihaspathee.zeus.dto.account.MemberDto;
import com.brihaspathee.zeus.dto.account.MemberPremiumDto;
import com.brihaspathee.zeus.exception.MemberNotFoundException;
import com.brihaspathee.zeus.helper.interfaces.EnrollmentSpanHelper;
import com.brihaspathee.zeus.helper.interfaces.PremiumSpanHelper;
import com.brihaspathee.zeus.mapper.interfaces.EnrollmentSpanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
     * Create an enrollment span
     * @param enrollmentSpanDto
     * @return
     */
    @Override
    public EnrollmentSpanDto createEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto) {
        EnrollmentSpan enrollmentSpan = enrollmentSpanMapper.enrollmentSpanDtoToEnrollmentSpan(enrollmentSpanDto);
        enrollmentSpan = enrollmentSpanRepository.save(enrollmentSpan);
        log.info("Created Enrollment span:{}", enrollmentSpan);
        return enrollmentSpanMapper.enrollmentSpanToEnrollmentSpanDto(enrollmentSpan);
    }

    /**
     * Get enrollment spans that match exchange subscriber id and state type code
     * @param exchangeSubscriberId
     * @param stateTypeCode
     * @return
     */
    @Override
    public List<EnrollmentSpan> getMatchingEnrollmentSpan(String exchangeSubscriberId, String stateTypeCode) {
        List<EnrollmentSpan> enrollmentSpans = enrollmentSpanRepository
                .findEnrollmentSpansByExchangeSubscriberIdAndStateTypeCode(exchangeSubscriberId,
                        stateTypeCode);
        return enrollmentSpans;
    }

    /**
     * Update an enrollment span
     * @param enrollmentSpanDto
     * @param accountDto
     */
    @Override
    public void updateEnrollmentSpan(EnrollmentSpanDto enrollmentSpanDto,
                                                  AccountDto accountDto) {
        EnrollmentSpan enrollmentSpan = enrollmentSpanMapper.enrollmentSpanDtoToEnrollmentSpan(enrollmentSpanDto);
        log.info("Enrollment span to be updated:{}", enrollmentSpan);
        enrollmentSpan = enrollmentSpanRepository.save(enrollmentSpan);
        log.info("Updated Enrollment span:{}", enrollmentSpan);
//        enrollmentSpanDto.getPremiumSpans().stream().forEach(premiumSpanDto -> {
//            // Check if the premium span already exists
//            if(premiumSpanDto.getPremiumSpanSK() == null){
//                // Premium span does not exist for the enrollment span
//                // so create it
//                premiumSpanDto.setEnrollmentSpanSK(enrollmentSpanDto.getEnrollmentSpanSK());
//                UUID premiumSpanSK = premiumSpanHelper.createPremiumSpan(premiumSpanDto).getPremiumSpanSK();
//                premiumSpanDto.setPremiumSpanSK(premiumSpanSK);
//            }else{
//                // Premium span exist check if it has to be updated
//                if(premiumSpanDto.getChanged().get() == true){
//                    // premium span has to be updated
//                    premiumSpanHelper.updatePremiumSpan(premiumSpanDto);
//                }
//            }
//        });
//        return enrollmentSpanMapper.enrollmentSpanToEnrollmentSpanDto(enrollmentSpan);
    }

    /**
     * Populate the member surrogate key to the member premium objects
     * @param memberPremiumDto
     * @param memberDtos
     */
    private void populateMemberSK(MemberPremiumDto memberPremiumDto, Set<MemberDto> memberDtos){
        MemberDto retrievedMember = memberDtos.stream()
                .filter(
                        memberDto ->
                                memberDto.getMemberCode().equals(memberPremiumDto.getMemberCode()))
                .findFirst()
                .orElseThrow(() -> {
                    throw new MemberNotFoundException("Member with member code " + memberPremiumDto.getMemberCode() + " not found");
                });
        memberPremiumDto.setMemberSK(retrievedMember.getMemberSK());
    }
}
