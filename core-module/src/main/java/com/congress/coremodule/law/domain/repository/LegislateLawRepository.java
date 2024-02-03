package com.congress.coremodule.law.domain.repository;

import com.congress.coremodule.law.domain.entity.LegislateLaw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegislateLawRepository extends JpaRepository<LegislateLaw, Long> {

    LegislateLaw findLegislateLawByName(String name);

    LegislateLaw findLegislateLawById(Long id);
}
