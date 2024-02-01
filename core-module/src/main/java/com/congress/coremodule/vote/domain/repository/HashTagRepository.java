package com.congress.coremodule.vote.domain.repository;

import com.congress.coremodule.vote.application.dto.HashTagRank;
import com.congress.coremodule.vote.domain.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    long countByUserIdAndLawId(String userId, Long lawId);

    @Query(value = "SELECT h.tag AS tag, COUNT(h) AS count FROM HashTag h GROUP BY h.tag ORDER BY count DESC", nativeQuery = true)
    List<HashTagRank> findTagCounts();
}