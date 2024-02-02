package com.congress.coremodule.vote.domain.repository;

import com.congress.coremodule.vote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT COUNT(*) FROM Vote v WHERE v.member.userId = :userId AND v.law.id = :lawId")
    long countByUserIdAndLawId(@Param("userId") String userId, @Param("lawId") Long lawId);
}
