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
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MyPageUseCase {

    private final MyPageQueryService myPageQueryService;
    private final VoteQueryService voteQueryService;
    private final LawQueryService lawQueryService;

    public List<MyPageAttendance> getMyLawAttendance(String userId) {

        Long memberId = myPageQueryService.getMemberId(userId);

        List<MyPageAttendance> myPageAttendanceList = new ArrayList<>();

        List<Long> hashTagLawIds = myPageQueryService.getHashTagLaws(memberId);
        List<Long> voteIds = myPageQueryService.getVoteLaws(memberId);

        List<Long> combinedList = Stream.concat(hashTagLawIds.stream(), voteIds.stream())
                .distinct()
                .toList();

        for (int i = 0; i < combinedList.size(); i++) {

            Long lawId = combinedList.get(i);

            String tag = myPageQueryService.getHashTagName(memberId, lawId);
            Integer score = myPageQueryService.getVoteScoreSingle(memberId, lawId);

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

        return myPageAttendanceList;
    }


    public List<MyPageLegislator> getMyLegislatorAttendance(String userId) {
        Long memberId = myPageQueryService.getMemberId(userId);
        List<Long> legislatorIds = myPageQueryService.getLegislatorIds(memberId);

        List<MyPageLegislator> myPageLegislatorList = new ArrayList<>();

        for (Long legislatorId : legislatorIds) {
            LegislateLaw law = lawQueryService.findLegislatorLaw(legislatorId);
            Integer score = myPageQueryService.getLegislatorVoteScore(law.getId(), memberId);
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
