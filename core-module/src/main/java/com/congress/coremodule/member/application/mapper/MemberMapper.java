package com.congress.coremodule.member.application.mapper;

import com.congress.coremodule.member.application.dto.MemberSignIn;
import com.congress.coremodule.member.domain.entity.Member;

public final class MemberMapper {

    private MemberMapper() {
    }

    public static Member toMember(MemberSignIn memberSignIn) {

        return Member.builder()
                .nickname(memberSignIn.getNickname())
                .email(memberSignIn.getEmail())
                .gender(memberSignIn.getGender())
                .year(memberSignIn.getYear())
                .intend(memberSignIn.getIntend())
                .isDeleted(false)
                .build();
    }
}
