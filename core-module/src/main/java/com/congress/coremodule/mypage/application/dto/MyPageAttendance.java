package com.congress.coremodule.mypage.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class MyPageAttendance {

    private String lawName;
    private Integer score;
    private String hashtag;
    private Integer totalScore;
}
