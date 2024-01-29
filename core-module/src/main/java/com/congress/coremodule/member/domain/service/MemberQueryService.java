package com.congress.coremodule.member.domain.service;

import com.congress.commonmodule.exception.Error;
import com.congress.coremodule.member.application.dto.MemberSignIn;
import com.congress.coremodule.member.application.mapper.MemberMapper;
import com.congress.coremodule.member.domain.entity.Member;
import com.congress.coremodule.member.domain.exception.MemberException;
import com.congress.coremodule.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public Boolean checkSignIn(String userId) {

        Member member = memberRepository.findMemberByUserId(userId);
        return member != null;
    }

    public void signIn(MemberSignIn memberSignIn) {

        Member member = MemberMapper.toMember(memberSignIn);
        memberRepository.save(member);
    }

    public void signOut(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(Error.MEMBER_NOT_FOUND));
        member.deleteMember();
    }
}
