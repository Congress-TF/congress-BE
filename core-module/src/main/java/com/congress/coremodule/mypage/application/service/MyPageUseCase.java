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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageUseCase {

    private final MyPageQueryService myPageQueryService;
    private final VoteQueryService voteQueryService;
    private final LawQueryService lawQueryService;

    public List<MyPageAttendance> getMyLawAttendance(String userId) {
        Long memberId = myPageQueryService.getMemberId(userId);
        List<String> tags = myPageQueryService.getHashTagNames(memberId);
        List<Long> lawIds = myPageQueryService.getLawIds(memberId);

        List<MyPageAttendance> myPageAttendanceList = new ArrayList<>();

        for (int i = 0; i < Math.min(tags.size(), lawIds.size()); i++) {
            String tag = tags.get(i);
            Long lawId = lawIds.get(i);

            Law law = lawQueryService.findLaw(lawId);
            Integer score = myPageQueryService.getVoteScore(law.getId());
            Integer totalScore = voteQueryService.getTotalScore(law.getName());

            MyPageAttendance myPageAttendance = MyPageAttendance.builder()
                    .lawName(law.getName())
                    .hashtag(tag)
                    .score(score)
                    .totalScore(totalScore)
                    .build();

            myPageAttendanceList.add(myPageAttendance);
        }

        return myPageAttendanceList;
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
