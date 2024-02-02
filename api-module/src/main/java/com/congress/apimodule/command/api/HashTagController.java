package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import com.congress.coremodule.vote.application.dto.HashTagInfo;
import com.congress.coremodule.vote.application.dto.HashTagRank;
import com.congress.coremodule.vote.application.service.VoteQueryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hashtag")
public class HashTagController {

    private final VoteQueryUseCase voteQueryUseCase;

    /**
     * 해시태그 저장
     */
    @PostMapping("/save")
    public ApplicationResponse<Void> saveHashTag(
            @RequestBody HashTagInfo hashTagInfo
    ) {

        voteQueryUseCase.saveHashTag(hashTagInfo);
        return ApplicationResponse.ok(null, "해시태그를 저장하였습니다.");
    }

    /**
     * 해시태그 순위 조회
     */
    @GetMapping("/{lawName}/rank")
    public ApplicationResponse<List<HashTagRank>> getHashTagRank(
            @PathVariable String lawName) {

        List<HashTagRank> results = voteQueryUseCase.getHashTagRank(lawName);
        return ApplicationResponse.ok(results, "해시태그 순위 조회 결과입니다.");
    }
}
