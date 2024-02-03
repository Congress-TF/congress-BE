package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import com.congress.coremodule.vote.application.dto.LegislatorVoteInfo;
import com.congress.coremodule.vote.application.dto.VoteInfo;
import com.congress.coremodule.vote.application.service.VoteQueryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vote")
public class VoteController {

    private final VoteQueryUseCase voteQueryUseCase;

    /**
     * 개정필요도 투표
     */
    @PostMapping()
    public ApplicationResponse<Void> voteRequirements(
            @RequestBody VoteInfo voteInfo) {

        voteQueryUseCase.saveRenewalScore(voteInfo);
        return ApplicationResponse.ok(null, "개정필요도 투표를 완료하였습니다.");
    }

    /**
     * 누적 개정 필요도 보여주기
     */
    @GetMapping("/{lawName}/total")
    public ApplicationResponse<Integer> voteSum(
            @PathVariable String lawName) {

        Integer total = voteQueryUseCase.getTotalScore(lawName);
        return ApplicationResponse.ok(total, "누적 개정 필요도 점수입니다.");
    }

    /**
     * 의정활동 참여도 국민투표
     */
    @PostMapping("/legislator")
    public ApplicationResponse<Void> voteLegislatorRequirements(
            @RequestBody LegislatorVoteInfo voteInfo) {

        voteQueryUseCase.saveLegislatorRenewalScore(voteInfo);
        return ApplicationResponse.ok(null, "개정필요도 투표를 완료하였습니다.");
    }


    /**
     * 누적 의정활동 참여도
     */
    @GetMapping("/{legislatorName}/legislator/total")
    public ApplicationResponse<Integer> voteLegislatorSum(
            @PathVariable String legislatorName) {

        Integer total = voteQueryUseCase.getLegislatorTotalScore(legislatorName);
        return ApplicationResponse.ok(total, "누적 의정활동 참여도 점수입니다.");
    }
}
