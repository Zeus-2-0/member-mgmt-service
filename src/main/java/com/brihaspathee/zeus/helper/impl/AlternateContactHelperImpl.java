package com.brihaspathee.zeus.helper.impl;

import com.brihaspathee.zeus.domain.entity.AlternateContact;
import com.brihaspathee.zeus.domain.repository.AlternateContactRepository;
import com.brihaspathee.zeus.dto.account.AlternateContactDto;
import com.brihaspathee.zeus.helper.interfaces.AlternateContactHelper;
import com.brihaspathee.zeus.mapper.interfaces.AlternateContactMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, November 2022
 * Time: 3:51 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.helper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AlternateContactHelperImpl implements AlternateContactHelper {

    /**
     * Mapper instance to map the dto to entity and vice versa
     */
    private final AlternateContactMapper alternateContactMapper;

    /**
     * Repository instance to perform CRUD operations
     */
    private final AlternateContactRepository alternateContactRepository;

    /**
     * Create the alternate contact
     * @param alternateContactDto
     * @return
     */
    @Override
    public AlternateContactDto createAlternateContact(AlternateContactDto alternateContactDto) {
        AlternateContact alternateContact =
                alternateContactMapper.alternateContactDtoToAlternateContact(alternateContactDto);
        alternateContact = alternateContactRepository.save(alternateContact);
        return alternateContactMapper.alternateContactToAlternateContactDto(alternateContact);
    }
}
