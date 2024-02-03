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
public class LegislatorList {

    private String name;
    private String section;
    private String unit;
    private Integer score;
}
