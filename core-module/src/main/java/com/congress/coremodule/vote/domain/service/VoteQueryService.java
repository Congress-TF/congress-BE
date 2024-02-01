package com.congress.coremodule.vote.domain.service;

import com.congress.commonmodule.exception.Error;
import com.congress.coremodule.law.domain.entity.Law;
import com.congress.coremodule.law.domain.repository.LawRepository;
import com.congress.coremodule.member.domain.entity.Member;
import com.congress.coremodule.member.domain.repository.MemberRepository;
import com.congress.coremodule.vote.application.dto.HashTagInfo;
import com.congress.coremodule.vote.application.dto.HashTagRank;
import com.congress.coremodule.vote.application.mapper.HashTagMapper;
import com.congress.coremodule.vote.domain.entity.HashTag;
import com.congress.coremodule.vote.domain.exception.VoteException;
import com.congress.coremodule.vote.domain.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteQueryService {

    private final HashTagRepository hashTagRepository;
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

    public List<HashTagRank> getHashTagRank() {

        return hashTagRepository.findTagCounts();
    }
}
