package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.MemberPremium;
import com.brihaspathee.zeus.domain.entity.PremiumSpan;
import com.brihaspathee.zeus.domain.repository.MemberPremiumRepository;
import com.brihaspathee.zeus.domain.repository.MemberRepository;
import com.brihaspathee.zeus.domain.repository.PremiumSpanRepository;
import com.brihaspathee.zeus.helper.interfaces.PremiumSpanHelper;
import com.brihaspathee.zeus.mapper.interfaces.PremiumSpanMapper;
import com.brihaspathee.zeus.web.model.MemberDto;
import com.brihaspathee.zeus.web.model.MemberPremiumDto;
import com.brihaspathee.zeus.web.model.PremiumSpanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, September 2022
 * Time: 4:34 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PremiumSpanHelperImpl implements PremiumSpanHelper {

    /**
     * Premium span repository to perform CRUD operations
     */
    private final PremiumSpanRepository premiumSpanRepository;

    /**
     * Mapper to convert the dtos to entities and vice versa
     */
    private final PremiumSpanMapper premiumSpanMapper;

    /**
     * Member premium span repository to perform CRUD operations
     */
    private final MemberPremiumRepository memberPremiumRepository;

    /**
     * Member Repository to perform CRUD operations
     */
    private final MemberRepository memberRepository;

    /**
     * Create the premium span and the associated member premium span
     * @param premiumSpanDto
     * @return
     */
    @Override
    public PremiumSpanDto createPremiumSpan(PremiumSpanDto premiumSpanDto) {
        final PremiumSpan premiumSpan = premiumSpanRepository.save(
                premiumSpanMapper.premiumSpanDtoToPremiumSpan(premiumSpanDto));
        Set<MemberPremium> memberPremiums = premiumSpan.getMembers();
        memberPremiums.stream().forEach(memberPremium -> memberPremium.setPremiumSpan(premiumSpan));
        memberPremiumRepository.saveAll(memberPremiums);
        return premiumSpanMapper.premiumSpanToPremiumSpanDto(premiumSpan);
    }


}
