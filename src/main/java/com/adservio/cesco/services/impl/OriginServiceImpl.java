package com.adservio.cesco.services.impl;

import com.adservio.cesco.domain.OriginMission;
import com.adservio.cesco.repositories.OriginMissionRepository;
import com.adservio.cesco.services.OriginService;
import com.adservio.cesco.web.mappers.OriginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OriginServiceImpl implements OriginService {

    private final OriginMissionRepository originMissionRepository;


    @Override
    public OriginMission createOriginMission(OriginMission originMission) {
        originMission.setId(null); //to make it generated
        originMission.setDateDebut(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        originMission.setActive(true);
        return this.originMissionRepository.save(originMission);
    }

    @Override
    public OriginMission updateOriginMission(OriginMission originMission) {
        return this.originMissionRepository.save(originMission);
    }

    @Override
    public Page<OriginMission> findAll(Pageable pageable) {

        return originMissionRepository.findAll(pageable);
    }

    @Override
    public boolean exist(Long originMissionId) {
        return originMissionRepository.existsById(originMissionId);
    }

    @Override
    public void deleteById(Long id) {
        originMissionRepository.deleteById(id);
    }

    @Override
    public void delete(OriginMission t) {
        originMissionRepository.delete(t);
    }
}
