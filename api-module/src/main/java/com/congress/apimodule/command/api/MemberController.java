package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import com.congress.coremodule.member.application.dto.MemberInfo;
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
     * 가입 여부 체크
     */
    @GetMapping("/check")
    public ApplicationResponse<Boolean> checkSignIn(@RequestParam String userId) {

        Boolean checkYn = memberQueryUseCase.checkSignIn(userId);
        return ApplicationResponse.ok(checkYn, "true : 가입 , false : 미가입");
    }

    /**
     * 내 정보 확인
     */
    @GetMapping("/myinfo")
    public ApplicationResponse<MemberInfo> getMyInfo(@RequestParam String userId) {

        MemberInfo memberInfo = memberQueryUseCase.getMyInfo(userId);
        return ApplicationResponse.ok(memberInfo, "내 정보 데이터입니다.");
    }

    /**
     * 내 정보 수정
     */
    @PutMapping("/update")
    public ApplicationResponse<String> updateMyInfo(@RequestBody MemberSignIn memberSignIn) {

        memberQueryUseCase.updateMyInfo(memberSignIn);
        return ApplicationResponse.ok(null, "회원 정보가 수정되었습니다.");
    }

    /**
     * 회원가입
     */
    @PostMapping("/signin")
    public ApplicationResponse<Void> signIn(@RequestBody MemberSignIn memberSignIn) {

        memberQueryUseCase.signIn(memberSignIn);
        return ApplicationResponse.ok(null, "회원가입이 완료되었습니다.");
    }

    /**
     * 회원탈퇴
     */
    @DeleteMapping("/{userId}/signout")
    public ApplicationResponse<Void> signout(@PathVariable String userId) {

        memberQueryUseCase.signOut(userId);
        return ApplicationResponse.ok(null, "회원탈퇴가 완료되었습니다.");
    }

}
