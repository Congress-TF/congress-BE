package com.congress.coremodule.vote.application.mapper;

import com.congress.coremodule.law.domain.entity.Law;
import com.congress.coremodule.member.domain.entity.Member;
import com.congress.coremodule.vote.domain.entity.HashTag;

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
}
