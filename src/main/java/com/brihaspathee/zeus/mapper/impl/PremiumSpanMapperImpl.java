package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.entity.EnrollmentSpan;
import com.brihaspathee.zeus.domain.entity.Member;
import com.brihaspathee.zeus.domain.entity.MemberPremium;
import com.brihaspathee.zeus.domain.entity.PremiumSpan;
import com.brihaspathee.zeus.dto.account.MemberPremiumDto;
import com.brihaspathee.zeus.dto.account.PremiumSpanDto;
import com.brihaspathee.zeus.mapper.interfaces.PremiumSpanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, September 2022
 * Time: 9:19 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class PremiumSpanMapperImpl implements PremiumSpanMapper {

    /**
     * Convert premium span dto to premium span entity
     * @param premiumSpanDto
     * @return
     */
    @Override
    public PremiumSpan premiumSpanDtoToPremiumSpan(PremiumSpanDto premiumSpanDto) {
        log.info("Inside premium span dto to premium span conversion:{}", premiumSpanDto);
        if(premiumSpanDto == null){
            return null;
        }
        PremiumSpan premiumSpan = PremiumSpan.builder()
                .premiumSpanSK(premiumSpanDto.getPremiumSpanSK())
                .premiumSpanCode(premiumSpanDto.getPremiumSpanCode())
                .enrollmentSpan(EnrollmentSpan.builder().enrollmentSpanSK(premiumSpanDto.getEnrollmentSpanSK()).build())
                .ztcn(premiumSpanDto.getZtcn())
                .source(premiumSpanDto.getSource())
                .startDate(premiumSpanDto.getStartDate())
                .endDate(premiumSpanDto.getEndDate())
                .statusTypeCode(premiumSpanDto.getStatusTypeCode())
                .sequence(premiumSpanDto.getSequence())
                .csrVariant(premiumSpanDto.getCsrVariant())
                .totalPremiumAmount(premiumSpanDto.getTotalPremiumAmount())
                .totalResponsibleAmount(premiumSpanDto.getTotalResponsibleAmount())
                .aptcAmount(premiumSpanDto.getAptcAmount())
                .otherPayAmount(premiumSpanDto.getOtherPayAmount())
                .csrAmount(premiumSpanDto.getCsrAmount())
                //.members(getMemberPremiums(premiumSpanDto.getMemberPremiumSpans()))
                .createdDate(premiumSpanDto.getCreatedDate())
                .updatedDate(premiumSpanDto.getUpdatedDate())
                .build();
        Set<MemberPremium> memberPremiums = getMemberPremiums(premiumSpanDto.getMemberPremiumSpans());
        premiumSpan.setMembers(memberPremiums);
        return premiumSpan;
    }

    /**
     * Convert premium span entity to premium span dto
     * @param premiumSpan
     * @return
     */
    @Override
    public PremiumSpanDto premiumSpanToPremiumSpanDto(PremiumSpan premiumSpan) {
        if(premiumSpan == null){
            return null;
        }
        PremiumSpanDto premiumSpanDto = PremiumSpanDto.builder()
                .premiumSpanSK(premiumSpan.getPremiumSpanSK())
                .premiumSpanCode(premiumSpan.getPremiumSpanCode())
                .enrollmentSpanSK(premiumSpan.getEnrollmentSpan().getEnrollmentSpanSK())
                .ztcn(premiumSpan.getZtcn())
                .source(premiumSpan.getSource())
                .startDate(premiumSpan.getStartDate())
                .endDate(premiumSpan.getEndDate())
                .statusTypeCode(premiumSpan.getStatusTypeCode())
                .sequence(premiumSpan.getSequence())
                .csrVariant(premiumSpan.getCsrVariant())
                .totalPremiumAmount(premiumSpan.getTotalPremiumAmount())
                .totalResponsibleAmount(premiumSpan.getTotalResponsibleAmount())
                .aptcAmount(premiumSpan.getAptcAmount())
                .otherPayAmount(premiumSpan.getOtherPayAmount())
                .csrAmount(premiumSpan.getCsrAmount())
                .memberPremiumSpans(getMemberPremiumDtos(premiumSpan.getMembers()))
                .createdDate(premiumSpan.getCreatedDate())
                .updatedDate(premiumSpan.getUpdatedDate())
                .build();
        return premiumSpanDto;
    }

    /**
     * Convert premium span dtos to premium span entities
     * @param premiumSpanDtos
     * @return
     */
    @Override
    public Set<PremiumSpan> premiumSpanDtosToPremiumSpans(Set<PremiumSpanDto> premiumSpanDtos) {
        if(premiumSpanDtos !=null && !premiumSpanDtos.isEmpty()){
            return premiumSpanDtos.stream().map(this::premiumSpanDtoToPremiumSpan).collect(Collectors.toSet());
        }else{
            return null;
        }

    }

    /**
     * Convert premium span entities to premium span dtos
     * @param premiumSpans
     * @return
     */
    @Override
    public Set<PremiumSpanDto> premiumSpansToPremiumSpanDtos(Set<PremiumSpan> premiumSpans) {
        if(premiumSpans !=null && !premiumSpans.isEmpty()){
            return premiumSpans.stream().map(this::premiumSpanToPremiumSpanDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * This method converts the member premium to member premium dto
     * @param memberPremium
     * @return
     */
    private MemberPremiumDto getMemberPremiumDto(MemberPremium memberPremium){
        if(memberPremium == null){
            return null;
        }
        MemberPremiumDto memberPremiumDto = MemberPremiumDto.builder()
                .memberPremiumSK(memberPremium.getMemberPremiumSK())
                .exchangeMemberId(memberPremium.getExchangeMemberId())
                .memberCode(memberPremium.getMember().getMemberCode())
                .individualPremiumAmount(memberPremium.getIndividualPremiumAmount())
                .memberSK(memberPremium.getMember().getMemberSK())
                .createdDate(memberPremium.getCreatedDate())
                .updatedDate(memberPremium.getUpdatedDate())
                .build();
        return memberPremiumDto;
    }

    /**
     * This method converts the member premium dto to member premium
     * @param memberPremiumDto
     * @return
     */
    private MemberPremium getMemberPremium(MemberPremiumDto memberPremiumDto){
        log.info("Inside get member premium method:{}", memberPremiumDto);
        if(memberPremiumDto == null){
            return null;
        }
        MemberPremium memberPremium = MemberPremium.builder()
                .memberPremiumSK(memberPremiumDto.getMemberPremiumSK())
                .exchangeMemberId(memberPremiumDto.getExchangeMemberId())
                .member(Member.builder()
                        .memberSK(memberPremiumDto.getMemberSK())
                        .memberCode(memberPremiumDto.getMemberCode())
                        .build())
                .individualPremiumAmount(memberPremiumDto.getIndividualPremiumAmount())
                .exchangeMemberId(memberPremiumDto.getExchangeMemberId())
                .createdDate(memberPremiumDto.getCreatedDate())
                .updatedDate(memberPremiumDto.getUpdatedDate())
                .build();
        return memberPremium;
    }

    /**
     * This method converts the member premiums to member premium dtos
     * @param memberPremiums
     * @return
     */
    private Set<MemberPremiumDto> getMemberPremiumDtos(Set<MemberPremium> memberPremiums){
        if(memberPremiums !=null && !memberPremiums.isEmpty()){
            return memberPremiums.stream().map(this::getMemberPremiumDto).collect(Collectors.toSet());
        }else{
            return null;
        }
    }

    /**
     * This method converts the member premiums dtos to member premiums
     * @param memberPremiumDtos
     * @return
     */
    private Set<MemberPremium> getMemberPremiums(Set<MemberPremiumDto> memberPremiumDtos){
        if(memberPremiumDtos !=null && !memberPremiumDtos.isEmpty()){
            Set<MemberPremium> memberPremiums = memberPremiumDtos
                    .stream()
                    .map(this::getMemberPremium)
                    .collect(Collectors.toSet());
            return memberPremiums;
        }else{
            return null;
        }
    }
}
