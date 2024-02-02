package com.congress.coremodule.law.domain.service;

import com.congress.coremodule.law.domain.entity.Law;
import com.congress.coremodule.law.domain.repository.LawRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LawQueryService {

    private final LawRepository lawRepository;

    public void saveLaw(String lawName) {

        final Law law = Law.builder().name(lawName).build();
        lawRepository.save(law);
    }

    public boolean isLawNameAlreadySaved(String lawName) {
        Optional<Law> existingLaw = Optional.ofNullable(lawRepository.findLawByName(lawName));
        return existingLaw.isPresent();
    }
}
