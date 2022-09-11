package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.web.model.MemberDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 7:44 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface MemberMapper {

    /**
     * Convert member dto to member entity
     * @param memberDto
     * @return
     */
    Member memberDtoToMember(MemberDto memberDto);

    /**
     * Convert member entity to member dto
     * @param member
     * @return
     */
    MemberDto memberToMemberDto(Member member);

    /**
     * Convert member dtos to member entities
     * @param memberDtos
     * @return
     */
    Set<Member> memberDtosToMembers(Set<MemberDto> memberDtos);

    /**
     * Convert member entities to member dtos
     * @param members
     * @return
     */
    Set<MemberDto> membersToMemberDtos(Set<Member> members);
}
