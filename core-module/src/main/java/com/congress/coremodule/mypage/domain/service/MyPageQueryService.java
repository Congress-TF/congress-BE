package com.congress.coremodule.mypage.domain.service;

import com.congress.coremodule.law.domain.entity.Law;
import com.congress.coremodule.law.domain.entity.LegislateLaw;
import com.congress.coremodule.law.domain.repository.LawRepository;
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
    private final LawRepository lawRepository;

    public Long getMemberId(String userId) {

        Member member = memberRepository.findMemberByUserId(userId);
        return member.getId();
    }

    public List<Long> getHashTagLaws(Long memberId) {
        List<HashTag> hashTags = hashTagRepository.findHashTagsByMemberId(memberId);
        List<Law> laws = hashTags.stream().map(HashTag::getLaw).toList();
        return laws.stream().map(Law::getId).toList();
    }

    public List<Long> getMyPageLaws(Long memberId) {
        List<LegislateVote> votes = legislateVoteRepository.findLegislateVotesByMemberId(memberId);
        List<LegislateLaw> laws = votes.stream().map(LegislateVote::getLegislateLaw).toList();
        return laws.stream().map(LegislateLaw::getId).toList();
    }

    public Long getLawSize() {

        return lawRepository.count();
    }

    public List<Long> getLawIds() {
        List<Law> laws = lawRepository.findAll();
        return laws.stream().map(Law::getId).collect(Collectors.toList());
    }

    public List<Long> getLegislatorIds(Long memberId) {

        return legislateVoteRepository
                .findLegislateVotesByMemberId(memberId)
                .stream()
                .map(LegislateVote::getLegislateLaw)
                .map(LegislateLaw::getId)
                .toList();
    }

    public String getHashTagName(Long memberId, Long lawId) {
        HashTag hashTag = hashTagRepository.findHashTagsByMemberIdAndLawId(memberId, lawId);
        return (hashTag != null) ? hashTag.getTag() : "";
    }

    public List<String> getHashTagNames(Long memberId) {
        List<HashTag> hashTag = hashTagRepository.findHashTagsByMemberId(memberId);
        return hashTag.stream().map(HashTag::getTag).collect(Collectors.toList());
    }

    public List<Integer> getVoteScores(Long memberId) {
        List<Vote> vote = voteRepository.findVotesByMemberId(memberId);
        return vote.stream().map(Vote::getScore).collect(Collectors.toList());
    }

    public Integer getVoteScoreSingle(Long memberId, Long lawId) {
        Vote vote = voteRepository.findVoteByMemberIdAndLawId(memberId, lawId);
        return (vote != null) ? vote.getScore() : 0;
    }

    public Integer getVoteScore(Long lawId) {
        Vote vote = voteRepository.findVoteByLawId(lawId);
        return (vote != null) ? vote.getScore() : 0;
    }

    public Integer getLegislatorVoteScore(Long lawId, Long memberId) {

        LegislateVote vote = legislateVoteRepository.findLegislateVoteByLegislateLawIdAndMemberId(lawId, memberId);
        return vote.getScore();
    }
}
