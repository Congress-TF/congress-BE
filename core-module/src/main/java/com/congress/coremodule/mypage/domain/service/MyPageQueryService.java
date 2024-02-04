package com.congress.coremodule.mypage.domain.service;

import com.congress.coremodule.member.domain.entity.Member;
import com.congress.coremodule.member.domain.repository.MemberRepository;
import com.congress.coremodule.vote.domain.entity.HashTag;
import com.congress.coremodule.vote.domain.entity.LegislateVote;
import com.congress.coremodule.vote.domain.entity.Vote;
import com.congress.coremodule.vote.domain.repository.HashTagRepository;
import com.congress.coremodule.vote.domain.repository.LegislateVoteRepository;
import com.congress.coremodule.vote.domain.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MyPageQueryService {

    private final MemberRepository memberRepository;
    private final HashTagRepository hashTagRepository;
    private final VoteRepository voteRepository;
    private final LegislateVoteRepository legislateVoteRepository;

    public Long getMemberId(String userId) {

        Member member = memberRepository.findMemberByUserId(userId);
        return member.getId();
    }

    public List<Long> getLawIds(Long memberId) {
        List<HashTag> hashTags = hashTagRepository.findHashTagsByMemberId(memberId);
        return hashTags.stream().map(hashTag -> hashTag.getLaw().getId()).collect(Collectors.toList());
    }

    public Long getLegislatorId(Long memberId) {

        LegislateVote vote = legislateVoteRepository.findLegislateVoteByMemberId(memberId);
        return vote.getLegislateLaw().getId();
    }

    public List<String> getHashTagNames(Long memberId) {
        List<HashTag> hashTag = hashTagRepository.findHashTagsByMemberId(memberId);
        return hashTag.stream().map(HashTag::getTag).collect(Collectors.toList());
    }

    public Integer getVoteScore(Long lawId) {
        Vote vote = voteRepository.findVoteByLawId(lawId);
        return (vote != null) ? vote.getScore() : 0;
    }

    public Integer getLegislatorVoteScore(Long memberId) {

        LegislateVote vote = legislateVoteRepository.findLegislateVoteByMemberId(memberId);
        return vote.getScore();
    }
}
