package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.service.interfaces.MemberService;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.model.AccountList;
import com.brihaspathee.zeus.web.model.MemberDto;
import com.brihaspathee.zeus.web.model.MemberList;
import com.brihaspathee.zeus.web.resource.interfaces.MemberAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 16, September 2022
 * Time: 7:11 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberAPIImpl implements MemberAPI {

    /**
     * Member service instance for performing operations
     */
    private final MemberService memberService;

    /**
     * Get member by member code
     * @param memberCode
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<MemberDto>> getMemberByCode(String memberCode) {
        MemberDto memberDto = memberService.getMemberByCode(memberCode);
        ZeusApiResponse<MemberDto> apiResponse = ZeusApiResponse.<MemberDto>builder()
                .response(memberDto)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.API_SUCCESS)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<MemberList>> getAllMembersOfAccount(String accountNumber) {
        MemberList memberList = MemberList.builder()
                .memberDtos(memberService.getMembersOfAccount(accountNumber))
                .build();;
        ZeusApiResponse<MemberList> apiResponse = ZeusApiResponse.<MemberList>builder()
                .response(memberList)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
