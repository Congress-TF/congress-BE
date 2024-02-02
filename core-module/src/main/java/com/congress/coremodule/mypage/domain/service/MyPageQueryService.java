package com.congress.coremodule.mypage.domain.service;

import com.congress.coremodule.member.domain.entity.Member;
import com.congress.coremodule.member.domain.repository.MemberRepository;
import com.congress.coremodule.vote.domain.entity.HashTag;
import com.congress.coremodule.vote.domain.entity.Vote;
import com.congress.coremodule.vote.domain.repository.HashTagRepository;
import com.congress.coremodule.vote.domain.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MyPageQueryService {

    private final MemberRepository memberRepository;
    private final HashTagRepository hashTagRepository;
    private final VoteRepository voteRepository;

    public Long getMemberId(String userId) {

        Member member = memberRepository.findMemberByUserId(userId);
        return member.getId();
    }

    public String getHashTagName(Long memberId) {

        HashTag hashTag = hashTagRepository.findHashTagByMemberId(memberId);
        return hashTag.getTag();
    }

    public Integer getVoteScore(Long memberId) {

        Vote vote = voteRepository.findVoteByMemberId(memberId);
        return vote.getScore();
    }
}
