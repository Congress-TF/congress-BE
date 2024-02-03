package com.congress.coremodule.vote.domain.entity;

import com.congress.coremodule.law.domain.entity.LegislateLaw;
import com.congress.coremodule.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class LegislateVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "legislatevote_id")
    private Long id;

    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "legislatelaw_id")
    private LegislateLaw legislateLaw;
}
