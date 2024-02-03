package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import com.congress.coremodule.law.application.dto.LawVoteReq;
import com.congress.coremodule.law.application.dto.LawVoteResult;
import com.congress.coremodule.law.application.dto.LegislatorReq;
import com.congress.coremodule.law.application.service.LawQueryUseCase;
import com.congress.coremodule.vote.application.dto.LawDetail;
import com.congress.coremodule.vote.application.dto.LawTotal;
import com.congress.coremodule.vote.application.dto.LegislatorDetail;
import com.congress.coremodule.vote.application.dto.LegislatorList;
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
@RequestMapping("/v1/law")
public class LawController {

    private final LawQueryUseCase lawQueryUseCase;

    /**
     * 개정 정족수 현황
     */
    @GetMapping("/voteresult")
    public ApplicationResponse<LawVoteResult> getVoteResult(
            @RequestParam String userId,
            @RequestParam String lawName) {

        final LawVoteReq lawVoteReq = LawVoteReq.builder().userId(userId).lawName(lawName).build();
        LawVoteResult result = lawQueryUseCase.getVoteResult(lawVoteReq);
        return ApplicationResponse.ok(result, "개정 정족수 현황입니다.");
    }

    /**
     * 의안 상세페이지 정보
     */
    @GetMapping("/detail")
    public ApplicationResponse<LawDetail> getLawDetail(
            @RequestParam String userId,
            @RequestParam String lawName) {

        final LawVoteReq lawVoteReq = LawVoteReq.builder().userId(userId).lawName(lawName).build();
        LawDetail result = lawQueryUseCase.getLawDetail(lawVoteReq);
        return ApplicationResponse.ok(result, "의안 상세페이지 정보입니다.");
    }

    /**
     * 전체 법률 목록
     */
    @GetMapping("/lists")
    public ApplicationResponse<List<LawTotal>> getLawLists() {

        List<LawTotal> result = lawQueryUseCase.getTotalLaws();
        return ApplicationResponse.ok(result, "법률 목록입니다.");
    }

    /**
     * 역대 국회의원 목록
     */
    @GetMapping("/legislator")
    public ApplicationResponse<List<LegislatorList>> getLegislatorLists() {

        List<LegislatorList> lists = lawQueryUseCase.getTotalLegislators();
        return ApplicationResponse.ok(lists, "역대 국회의원 목록입니다.");
    }

    /**
     * 국회의원 상세 페이지
     */
    @GetMapping("/legislator/detail")
    public ApplicationResponse<LegislatorDetail> getLegislatorDetail(
            @RequestParam String userId,
            @RequestParam String legislatorName) {

        final LegislatorReq legislatorReq = LegislatorReq.builder().userId(userId).legislatorName(legislatorName).build();
        LegislatorDetail result = lawQueryUseCase.getLegislatorDetail(legislatorReq);
        return ApplicationResponse.ok(result, "국회의원 상세페이지 정보입니다.");
    }

}
