package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberLanguage;
import com.brihaspathee.zeus.dto.account.MemberLanguageDto;
import com.brihaspathee.zeus.mapper.interfaces.MemberLanguageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 6:42 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class MemberLanguageMapperImpl implements MemberLanguageMapper {

    /**
     * Convert language dto to language
     * @param languageDto
     * @return
     */
    @Override
    public MemberLanguage languageDtoToLanguage(MemberLanguageDto languageDto) {
        if(languageDto == null){
            return null;
        }
        MemberLanguage language = MemberLanguage.builder()
                .memberLanguageSK(languageDto.getMemberLanguageSK())
                .memberLanguageCode(languageDto.getMemberLanguageCode())
                .member(Member.builder().memberSK(languageDto.getMemberSK()).build())
                .languageTypeCode(languageDto.getLanguageTypeCode())
                .languageCode(languageDto.getLanguageCode())
                .startDate(languageDto.getStartDate())
                .endDate(languageDto.getEndDate())
                .createdDate(languageDto.getCreatedDate())
                .updatedDate(languageDto.getUpdatedDate())
                .build();
        return language;
    }

    /**
     * Convert language entity to language dto
     * @param language
     * @return
     */
    @Override
    public MemberLanguageDto languageToLanguageDto(MemberLanguage language) {
        if(language == null){
            return null;
        }
        MemberLanguageDto languageDto = MemberLanguageDto.builder()
                .memberLanguageSK(language.getMemberLanguageSK())
                .memberLanguageCode(language.getMemberLanguageCode())
                .memberSK(language.getMember().getMemberSK())
                .languageTypeCode(language.getLanguageTypeCode())
                .languageCode(language.getLanguageCode())
                .startDate(language.getStartDate())
                .endDate(language.getEndDate())
                .createdDate(language.getCreatedDate())
                .updatedDate(language.getUpdatedDate())
                .build();
        return languageDto;
    }

    /**
     * Convert language dtos to language entities
     * @param languageDtos
     * @return
     */
    @Override
    public Set<MemberLanguage> languageDtosToLanguages(Set<MemberLanguageDto> languageDtos) {
        if(languageDtos !=null && !languageDtos.isEmpty()){
            return languageDtos.stream().map(this::languageDtoToLanguage).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * Convert language entites to language DTOs
     * @param languages
     * @return
     */
    @Override
    public Set<MemberLanguageDto> languagesToLanguageDtos(Set<MemberLanguage> languages) {
        if(languages !=null && !languages.isEmpty()){
            return languages.stream().map(this::languageToLanguageDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
}
