package com.congress.coremodule.vote.domain.repository;

import com.congress.coremodule.vote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT COUNT(*) FROM Vote v WHERE v.member.userId = :userId AND v.law.name = :lawName")
    long countByUserIdAndLawName(@Param("userId") String userId, @Param("lawName") String lawName);

    List<Vote> findVotesByLawName(String name);

    List<Vote> findVotesByMemberId(Long memberId);
    Vote findVoteByLawId(Long lawId);
}
