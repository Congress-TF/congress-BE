package com.congress.coremodule.vote.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class LegislatorDetail {

    private String hgNm;
    private String bthDate;
    private String sexGbnNm;
    private String reeleGbnNm;
    private String units;
    private String unitNm;
    private String polyNm;
    private String origNm;

    private String ftToDateOne;
    private String profileSjOne;

    private String frToDateTwo;
    private String profileSjTwo;

}
