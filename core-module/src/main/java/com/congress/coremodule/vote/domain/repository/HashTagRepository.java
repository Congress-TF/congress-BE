package com.congress.coremodule.vote.domain.repository;

import com.congress.coremodule.vote.application.dto.HashTagRank;
import com.congress.coremodule.vote.domain.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    @Query("SELECT COUNT(*) FROM HashTag h JOIN Member m JOIN Law l WHERE m.userId = :userId AND l.id = :lawId")
    long countByUserIdAndLawId(@Param("userId") String userId, @Param("lawId") Long lawId);

    @Query(value = "SELECT h.tag AS tag, COUNT(h) AS count FROM HashTag h GROUP BY h.tag ORDER BY count DESC")
    List<HashTagRank> findTagCounts();
}