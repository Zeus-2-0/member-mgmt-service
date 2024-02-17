package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.MemberLanguage;
import com.brihaspathee.zeus.domain.repository.MemberLanguageRepository;
import com.brihaspathee.zeus.dto.account.MemberLanguageDto;
import com.brihaspathee.zeus.helper.interfaces.MemberLanguageHelper;
import com.brihaspathee.zeus.mapper.interfaces.MemberLanguageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 18, September 2022
 * Time: 5:20 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberLanguageHelperImpl implements MemberLanguageHelper {

    /**
     * Repository for CRUD operations
     */
    private final MemberLanguageRepository memberLanguageRepository;

    /**
     * Mapper to convert dtos to entities and vice versa
     */
    private final MemberLanguageMapper memberLanguageMapper;

    /**
     * Create member language
     * @param memberLanguageDto
     * @return the created member language
     */
    @Override
    public MemberLanguageDto createMemberLanguage(MemberLanguageDto memberLanguageDto) {
        MemberLanguage memberLanguage = memberLanguageMapper.languageDtoToLanguage(memberLanguageDto);
        memberLanguage = memberLanguageRepository.save(memberLanguage);
        return memberLanguageMapper.languageToLanguageDto(memberLanguage);
    }

    /**
     * Save member languages
     * @param memberLanguageDtos
     */
    @Override
    public void saveMemberLanguages(Set<MemberLanguageDto> memberLanguageDtos) {
        if(memberLanguageDtos == null || memberLanguageDtos.isEmpty()){
            return;
        }
        memberLanguageDtos.forEach(memberLanguageDto -> {
            if(memberLanguageDto.getChanged().get()){
                createMemberLanguage(memberLanguageDto);
            }
        });
    }
}
