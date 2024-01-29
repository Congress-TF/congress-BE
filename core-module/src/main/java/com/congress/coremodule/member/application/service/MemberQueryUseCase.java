package com.congress.coremodule.member.application.service;

import com.congress.commonmodule.exception.Error;
import com.congress.coremodule.member.application.dto.MemberSignIn;
import com.congress.coremodule.member.domain.exception.MemberException;
import com.congress.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryUseCase {

    private final MemberQueryService memberQueryService;

    public Boolean checkSignIn(String userId) {

        return memberQueryService.checkSignIn(userId);
    }

    public void signIn(MemberSignIn memberSignIn) throws MemberException {

        Boolean check = memberQueryService.checkSignIn(memberSignIn.getUserId());

        if (check) {
            throw new MemberException(Error.DUPLICATE_MEMBER);
        } else {
            memberQueryService.signIn(memberSignIn);
        }
    }

    public void signOut(Long memberId) {

        memberQueryService.signOut(memberId);
    }
}
