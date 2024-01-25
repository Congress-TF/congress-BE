package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import com.congress.coremodule.member.application.dto.MemberSignIn;
import com.congress.coremodule.member.application.service.MemberQueryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberQueryUseCase memberQueryUseCase;

    /**
     * 회원가입
     */
    @PostMapping("/signin")
    public ApplicationResponse<Void> signin(@RequestBody MemberSignIn memberSignIn) {

        memberQueryUseCase.signIn(memberSignIn);
        return ApplicationResponse.ok(null, "회원가입이 완료되었습니다.");
    }

    /**
     * 회원탈퇴
     */
    @DeleteMapping("/{memberId}/signout")
    public ApplicationResponse<Void> signout(@PathVariable Long memberId) {

        memberQueryUseCase.signOut(memberId);
        return ApplicationResponse.ok(null, "회원탈퇴가 완료되었습니다.");
    }

}
