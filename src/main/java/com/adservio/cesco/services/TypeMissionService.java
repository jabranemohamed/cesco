package com.adservio.cesco.services;

import com.adservio.cesco.domain.TypeMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeMissionService {

    TypeMission createTypeMission(TypeMission typeMission);

    TypeMission updateTypeMission(TypeMission typeMission);

    Page<TypeMission> findAll(Pageable pageable);

    void deleteById(Long id);

    void delete(TypeMission t);
}
