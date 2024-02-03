package com.congress.coremodule.law.domain.repository;

import com.congress.coremodule.law.domain.entity.Law;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawRepository extends JpaRepository<Law, Long> {

    Law findLawByName(String name);

    Law findLawById(Long lawId);
}
