package com.congress.coremodule.vote.domain.service;

import com.congress.commonmodule.exception.Error;
import com.congress.coremodule.law.domain.entity.Law;
import com.congress.coremodule.law.domain.repository.LawRepository;
import com.congress.coremodule.member.domain.entity.Member;
import com.congress.coremodule.member.domain.repository.MemberRepository;
import com.congress.coremodule.vote.application.dto.HashTagInfo;
import com.congress.coremodule.vote.application.dto.HashTagRank;
import com.congress.coremodule.vote.application.dto.VoteInfo;
import com.congress.coremodule.vote.application.mapper.HashTagMapper;
import com.congress.coremodule.vote.domain.entity.HashTag;
import com.congress.coremodule.vote.domain.entity.Vote;
import com.congress.coremodule.vote.domain.exception.VoteException;
import com.congress.coremodule.vote.domain.repository.HashTagRepository;
import com.congress.coremodule.vote.domain.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteQueryService {

    private final HashTagRepository hashTagRepository;
    private final VoteRepository voteRepository;
    private final MemberRepository memberRepository;
    private final LawRepository lawRepository;

    public void saveHashTag(HashTagInfo hashTagInfo) {

        if (hashTagRepository.countByUserIdAndLawId(hashTagInfo.getUserId(), hashTagInfo.getLawId()) >= 1) {
            throw new VoteException(Error.HASHTAG_DUPLICATE);
        } else {

            Member member = memberRepository.findMemberByUserId(hashTagInfo.getUserId());
            Law law = lawRepository.findLawById(hashTagInfo.getLawId());

            HashTag hashTag = HashTagMapper.toHashTag(hashTagInfo.getTag(), member, law);
            hashTagRepository.save(hashTag);
        }
    }

    public List<HashTagRank> getHashTagRank(String lawName) {

        return hashTagRepository.findTagCounts(lawName);
    }

    public void saveVote(VoteInfo voteInfo) {

        if (voteRepository.countByUserIdAndLawId(voteInfo.getUserId(), voteInfo.getLawId()) >= 1) {
            throw new VoteException(Error.VOTE_DUPLICATE);
        } else {

            Member member = memberRepository.findMemberByUserId(voteInfo.getUserId());
            Law law = lawRepository.findLawById(voteInfo.getLawId());

            Vote vote = HashTagMapper.toVote(voteInfo.getScore(), member, law);
            voteRepository.save(vote);
        }
    }

    public Integer getTotalScore(String lawName) {

        List<Vote> votes = voteRepository.findVotesByLawName(lawName);

        return votes.stream()
                .map(Vote::getScore)
                .reduce(0, Integer::sum);
    }
}
