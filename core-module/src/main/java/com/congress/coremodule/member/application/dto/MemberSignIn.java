package com.congress.coremodule.member.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberSignIn {

    private String nickname;
    private String gender;
    private String year;
    private String email;
    private String intend;
}
