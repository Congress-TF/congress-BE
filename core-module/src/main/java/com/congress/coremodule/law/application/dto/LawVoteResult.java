package com.congress.coremodule.law.application.dto;

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
public class LawVoteResult {

    private String billNm;
    private String yesCount;
}
