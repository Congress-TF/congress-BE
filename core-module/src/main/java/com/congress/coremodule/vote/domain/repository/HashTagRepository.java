package com.congress.coremodule.vote.domain.repository;

import com.congress.coremodule.vote.application.dto.HashTagRank;
import com.congress.coremodule.vote.domain.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    @Query("SELECT COUNT(*) FROM HashTag h WHERE h.member.userId = :userId AND h.law.name = :lawName")
    long countByUserIdAndLawName(@Param("userId") String userId, @Param("lawName") String lawName);

    @Query("SELECT new com.congress.coremodule.vote.application.dto.HashTagRank(h.tag, COUNT(h)) FROM HashTag h " +
            "where h.law.name = :lawName GROUP BY h.tag ORDER BY COUNT(h) DESC")
    List<HashTagRank> findTagCounts(@Param("lawName") String lawName);

    HashTag findHashTagByMemberId(Long memberId);
}