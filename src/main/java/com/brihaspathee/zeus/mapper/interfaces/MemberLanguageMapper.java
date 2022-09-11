package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.MemberLanguage;
import com.brihaspathee.zeus.web.model.MemberLanguageDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 6:37 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberLanguageMapper {
    /**
     * Convert language dto to language
     * @param languageDto
     * @return
     */
    MemberLanguage languageDtoToLanguage(MemberLanguageDto languageDto);

    /**
     * Convert language entity to language dto
     * @param language
     * @return
     */
    MemberLanguageDto languageToLanguageDto(MemberLanguage language);

    /**
     * Convert language dtos to language entities
     * @param languageDtos
     * @return
     */
    Set<MemberLanguage> languageDtosToLanguages(Set<MemberLanguageDto> languageDtos);

    /**
     * Convert language entites to language DTOs
     * @param languages
     * @return
     */
    Set<MemberLanguageDto> languagesToLanguageDtos(Set<MemberLanguage> languages);
}
