package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import com.congress.coremodule.mypage.application.dto.MyPageAttendance;
import com.congress.coremodule.mypage.application.dto.MyPageLegislator;
import com.congress.coremodule.mypage.application.service.MyPageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mypage")
public class MyPageController {

    private final MyPageUseCase myPageUseCase;

    /**
     * 내 의견 목록 (개정 필요도)
     */
    @GetMapping("/vote")
    public ApplicationResponse<List<MyPageAttendance>> getMyVoteAttendance(
            @RequestParam String userId
    ) {

        List<MyPageAttendance> attendance = myPageUseCase.getMyLawAttendance(userId);
        return ApplicationResponse.ok(attendance, "내 개정 필요도 의견 목록입니다.");
    }

    /**
     * 내 의견 목록 (의정활동 참여도)
     */
    @GetMapping("/legislator")
    public ApplicationResponse<List<MyPageLegislator>> getMyLegislatorAttendance(
            @RequestParam String userId
    ) {

        List<MyPageLegislator> attendance = myPageUseCase.getMyLegislatorAttendance(userId);
        return ApplicationResponse.ok(attendance, "내 의정활동 참여도 목록입니다.");
    }
}
