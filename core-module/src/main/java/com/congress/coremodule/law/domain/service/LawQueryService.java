package com.congress.coremodule.law.domain.service;

import com.congress.coremodule.law.domain.entity.Law;
import com.congress.coremodule.law.domain.entity.LegislateLaw;
import com.congress.coremodule.law.domain.repository.LawRepository;
import com.congress.coremodule.law.domain.repository.LegislateLawRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LawQueryService {

    private final LawRepository lawRepository;
    private final LegislateLawRepository legislateLawRepository;

    public void saveLaw(String lawName) {

        final Law law = Law.builder().name(lawName).build();
        lawRepository.save(law);
    }

    public boolean isLawAlreadySaved(String lawName) {
        Optional<Law> existingLaw = Optional.ofNullable(lawRepository.findLawByName(lawName));
        return existingLaw.isPresent();
    }

    public void saveLegislator(String lawName) {

        final LegislateLaw legislateLaw = LegislateLaw.builder().name(lawName).build();
        legislateLawRepository.save(legislateLaw);
    }

    public boolean isLegislatorAlreadySaved(String lawName) {
        Optional<LegislateLaw> existingLaw = Optional.ofNullable(legislateLawRepository.findLegislateLawByName(lawName));
        return existingLaw.isPresent();
    }
}
