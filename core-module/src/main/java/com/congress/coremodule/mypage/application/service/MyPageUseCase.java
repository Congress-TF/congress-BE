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
        List<Integer> scores = myPageQueryService.getVoteScores(memberId);
        List<Long> lawIds = myPageQueryService.getLawIds(memberId);

        List<MyPageAttendance> myPageAttendanceList = new ArrayList<>();

        int maxSize = Math.max(tags.size(), Math.max(scores.size(), lawIds.size()));

        for (int i = 0; i < maxSize; i++) {
            String tag = (i < tags.size()) ? tags.get(i) : "";
            Long lawId = (i < lawIds.size()) ? lawIds.get(i) : null;
            Integer score = (i < scores.size()) ? scores.get(i) : 0;

            if (lawId != null) {
                Law law = lawQueryService.findLaw(lawId);
                Integer totalScore = voteQueryService.getTotalScore(law.getName());

                MyPageAttendance myPageAttendance = MyPageAttendance.builder()
                        .lawName(law.getName())
                        .hashtag(tag)
                        .score(score)
                        .totalScore(totalScore)
                        .build();

                myPageAttendanceList.add(myPageAttendance);
            }
        }

        return myPageAttendanceList;
    }


    public List<MyPageLegislator> getMyLegislatorAttendance(String userId) {
        Long memberId = myPageQueryService.getMemberId(userId);
        List<Long> legislatorIds = myPageQueryService.getLegislatorIds(memberId);

        List<MyPageLegislator> myPageLegislatorList = new ArrayList<>();

        for (Long legislatorId : legislatorIds) {
            LegislateLaw law = lawQueryService.findLegislatorLaw(legislatorId);
            Integer score = myPageQueryService.getLegislatorVoteScore(law.getId());
            Integer totalScore = voteQueryService.getLegislatorTotalScore(law.getName());

            MyPageLegislator myPageLegislator = MyPageLegislator.builder()
                    .legislatorName(law.getName())
                    .score(score)
                    .totalScore(totalScore)
                    .build();

            myPageLegislatorList.add(myPageLegislator);
        }

        return myPageLegislatorList;
    }

}
