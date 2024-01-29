package com.congress.coremodule.member.domain.repository;

import com.congress.coremodule.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByUserId(String userId);
}
