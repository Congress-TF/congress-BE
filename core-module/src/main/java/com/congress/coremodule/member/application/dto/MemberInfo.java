package com.congress.coremodule.member.application.dto;

import lombok.Builder;

@Builder
public record MemberInfo(String nickname, String gender, String year) {
}
