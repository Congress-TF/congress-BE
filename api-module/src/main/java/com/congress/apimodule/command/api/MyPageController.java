package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import com.congress.coremodule.member.application.dto.MemberInfo;
import com.congress.coremodule.mypage.application.dto.MyPageAttendance;
import com.congress.coremodule.mypage.application.service.MyPageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mypage")
public class MyPageController {

    private final MyPageUseCase myPageUseCase;

    /**
     * 내 의견 목록
     */
    @GetMapping("/vote")
    public ApplicationResponse<MyPageAttendance> getMyAttendance(
            @RequestParam String userId
    ) {

        MyPageAttendance attendance = myPageUseCase.getMyAttendance(userId);
        return ApplicationResponse.ok(attendance, "내 의견 목록입니다.");
    }

    /**
     * 내 정보 확인
     */
    @GetMapping("/info")

    public ApplicationResponse<MemberInfo> getMyInfo(
            @RequestParam String userId) {

        MemberInfo memberInfo = myPageUseCase.getMyPageInfo(userId);
        return ApplicationResponse.ok(memberInfo, "내 정보입니다.");
    }

    /**
     * 내 정보 수정
     */
}
