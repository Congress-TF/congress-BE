package com.congress.coremodule.vote.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class HashTagInfo {

    private String userId;
    private Long lawId;
    private String tag;
}
