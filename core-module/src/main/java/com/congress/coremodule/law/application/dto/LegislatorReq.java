package com.congress.coremodule.law.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class LegislatorReq {

    private String userId;
    private String legislatorName;
}
