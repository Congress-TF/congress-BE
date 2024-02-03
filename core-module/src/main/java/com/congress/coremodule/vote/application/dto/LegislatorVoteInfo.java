package com.congress.coremodule.vote.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class LegislatorVoteInfo {

    private String userId;
    private String legislatorName;
    private Integer score;
}
