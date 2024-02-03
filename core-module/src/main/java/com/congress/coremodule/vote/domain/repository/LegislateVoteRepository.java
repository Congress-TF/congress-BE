package com.congress.coremodule.vote.domain.repository;

import com.congress.coremodule.law.domain.entity.LegislateLaw;
import com.congress.coremodule.vote.domain.entity.LegislateVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LegislateVoteRepository extends JpaRepository<LegislateVote, Long> {

    @Query("SELECT COUNT(*) FROM LegislateVote v WHERE v.member.userId = :userId AND v.legislateLaw.name = :legislatorName")
    long countByUserIdAndLegislatorName(@Param("userId") String userId, @Param("legislatorName") String legislatorName);

    List<LegislateVote> findLegislateVotesByLegislateLawName(String name);

    LegislateVote findLegislateVoteByMemberId(Long memberId);
}
