package com.congress.coremodule.mypage.application.service;

import com.congress.coremodule.law.domain.entity.Law;
import com.congress.coremodule.law.domain.entity.LegislateLaw;
import com.congress.coremodule.law.domain.service.LawQueryService;
import com.congress.coremodule.mypage.application.dto.MyPageAttendance;
import com.congress.coremodule.mypage.application.dto.MyPageLegislator;
import com.congress.coremodule.mypage.domain.service.MyPageQueryService;
import com.congress.coremodule.vote.domain.service.VoteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageUseCase {

    private final MyPageQueryService myPageQueryService;
    private final VoteQueryService voteQueryService;
    private final LawQueryService lawQueryService;

    public MyPageAttendance getMyLawAttendance(String userId) {

        Long memberId = myPageQueryService.getMemberId(userId);
        String tag = myPageQueryService.getHashTagName(memberId);
        Long lawId = myPageQueryService.getLawId(memberId);
        Integer score = myPageQueryService.getVoteScore(memberId);

        Law law = lawQueryService.findLaw(lawId);
        Integer totalScore = voteQueryService.getTotalScore(law.getName());

        return MyPageAttendance.builder()
                .lawName(law.getName())
                .hashtag(tag)
                .score(score)
                .totalScore(totalScore)
                .build();
    }

    public MyPageLegislator getMyLegislatorAttendance(String userId) {

        Long memberId = myPageQueryService.getMemberId(userId);
        Long legislatorId = myPageQueryService.getLegislatorId(memberId);
        Integer score = myPageQueryService.getLegislatorVoteScore(memberId);

        LegislateLaw law = lawQueryService.findLegislatorLaw(legislatorId);
        Integer totalScore = voteQueryService.getLegislatorTotalScore(law.getName());

        return MyPageLegislator.builder()
                .legislatorName(law.getName())
                .score(score)
                .totalScore(totalScore)
                .build();
    }
}
