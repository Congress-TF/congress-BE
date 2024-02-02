package com.congress.coremodule.mypage.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class MyPageAttendance {

    private String lawName;
    private String proposer;
    private String date;
    private Integer score;
    private String hashtag;
}
