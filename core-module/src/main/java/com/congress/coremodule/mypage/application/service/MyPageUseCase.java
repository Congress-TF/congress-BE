package com.congress.coremodule.mypage.application.service;

import com.congress.coremodule.member.application.dto.MemberInfo;
import com.congress.coremodule.member.domain.service.MemberQueryService;
import com.congress.coremodule.mypage.application.dto.MyPageAttendance;
import com.congress.coremodule.mypage.domain.service.MyPageQueryService;
import com.congress.coremodule.vote.domain.service.VoteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageUseCase {

    private final MemberQueryService memberQueryService;
    private final MyPageQueryService myPageQueryService;
    private final VoteQueryService voteQueryService;

    public MemberInfo getMyPageInfo(String userId) {

        return memberQueryService.getMyInfo(userId);
    }

    public MyPageAttendance getMyAttendance(String userId) {

        Long memberId = myPageQueryService.getMemberId(userId);
        String tag = myPageQueryService.getHashTagName(memberId);
        Integer score = myPageQueryService.getVoteScore(memberId);

        return MyPageAttendance.builder()
                .hashtag(tag)
                .score(score).build();
    }
}
