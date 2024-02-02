package com.congress.coremodule.vote.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class LawDetail {

    private String billNo;
    private String billNm;
    private String proposer;
    private String proposerDt;
    private String detailLink;
}
