package com.congress.coremodule.law.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class LawVoteReq {

    private String userId;
    private String lawName;
}
