package com.congress.coremodule.vote.domain.repository;

import com.congress.coremodule.vote.domain.entity.LegislateVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegislateVoteRepository extends JpaRepository<LegislateVote, Long> {

    List<LegislateVote> findLegislateVotesByLegislateLawName(String name);
}
