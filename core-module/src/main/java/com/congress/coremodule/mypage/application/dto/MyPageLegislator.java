package com.congress.coremodule.mypage.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class MyPageLegislator {

    private String legislatorName;
    private Integer score;
    private Integer totalScore;
}
