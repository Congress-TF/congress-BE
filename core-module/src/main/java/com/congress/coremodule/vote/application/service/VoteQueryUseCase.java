package com.congress.coremodule.vote.application.service;

import com.congress.coremodule.vote.application.dto.HashTagInfo;
import com.congress.coremodule.vote.application.dto.HashTagRank;
import com.congress.coremodule.vote.application.dto.VoteInfo;
import com.congress.coremodule.vote.domain.service.VoteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteQueryUseCase {

    private final VoteQueryService voteQueryService;

    public void saveHashTag(HashTagInfo hashTagInfo) {

        voteQueryService.saveHashTag(hashTagInfo);
    }

    public List<HashTagRank> getHashTagRank(Long lawId) {

        return voteQueryService.getHashTagRank(lawId);
    }

    public void saveRenewalScore(VoteInfo voteInfo) {

        voteQueryService.saveVote(voteInfo);
    }

    public Integer getTotalScore(Long lawId) {

        return voteQueryService.getTotalScore(lawId);
    }
}
