package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import com.congress.coremodule.law.application.dto.LawVoteReq;
import com.congress.coremodule.law.application.dto.LawVoteResult;
import com.congress.coremodule.law.application.service.LawQueryUseCase;
import com.congress.coremodule.vote.application.dto.LawDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestBody LawVoteReq lawVoteReq) {

        LawVoteResult result = lawQueryUseCase.getVoteResult(lawVoteReq);
        return ApplicationResponse.ok(result, "개정 정족수 현황입니다.");
    }

    /**
     * 의안 상세페이지 정보
     */
    @GetMapping("/detail")
    public ApplicationResponse<LawDetail> getLawDeail(
            @RequestBody LawVoteReq lawVoteReq) {

        LawDetail result = lawQueryUseCase.getLawDetail(lawVoteReq);
        return ApplicationResponse.ok(result, "의안 상세페이지 정보입니다.");
    }

    /**
     * 전체 법률 목록
     */
}
