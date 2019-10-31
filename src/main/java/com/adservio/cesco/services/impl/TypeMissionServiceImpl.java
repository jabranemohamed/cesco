package com.adservio.cesco.services.impl;

import com.adservio.cesco.domain.TypeMission;
import com.adservio.cesco.repositories.TypeMissionRepository;
import com.adservio.cesco.services.TypeMissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class TypeMissionServiceImpl implements TypeMissionService {

    private final TypeMissionRepository typeMissionRepository;

    public TypeMissionServiceImpl(TypeMissionRepository typeMissionRepository) {
        this.typeMissionRepository = typeMissionRepository;
    }

    @Override
    public TypeMission createTypeMission(TypeMission typeMission) {
        return null;
    }

    @Override
    public TypeMission updateTypeMission(TypeMission typeMission) {
        return null;
    }

    @Override
    public Page<TypeMission> findAll(Pageable pageable) {
        return typeMissionRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        typeMissionRepository.deleteById(id);
    }

    @Override
    public void delete(TypeMission t) {
        typeMissionRepository.delete(t);
    }
}
