package com.congress.coremodule.vote.application.mapper;

import com.congress.coremodule.law.domain.entity.Law;
import com.congress.coremodule.law.domain.entity.LegislateLaw;
import com.congress.coremodule.member.domain.entity.Member;
import com.congress.coremodule.vote.domain.entity.HashTag;
import com.congress.coremodule.vote.domain.entity.LegislateVote;
import com.congress.coremodule.vote.domain.entity.Vote;

public class HashTagMapper {

    private HashTagMapper() {
    }

    public static HashTag toHashTag(String tag, Member member, Law law) {

        return HashTag.builder()
                .tag(tag)
                .member(member)
                .law(law)
                .build();
    }

    public static Vote toVote(Integer score, Member member, Law law) {

        return Vote.builder()
                .score(score)
                .member(member)
                .law(law)
                .build();
    }

    public static LegislateVote toLegislatorVote(Integer score, Member member, LegislateLaw law) {

        return LegislateVote.builder()
                .score(score)
                .member(member)
                .legislateLaw(law)
                .build();
    }
}
