package com.adservio.cesco.services.impl;

import com.adservio.cesco.domain.Station;
import com.adservio.cesco.repositories.StationRepository;
import com.adservio.cesco.services.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;


    @Override
    public Station createStation(Station originMission) {
        return stationRepository.save(originMission);
    }

    @Override
    public Station updateStation(Station originMission) {
        return stationRepository.save(originMission);
    }

    @Override
    public Page<Station> findAll(Pageable pageable) {
        return stationRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        stationRepository.deleteById(id);
    }

    @Override
    public void delete(Station t) {
        stationRepository.delete(t);
    }

    @Override
    public boolean exist(Long stationId) {
        return stationRepository.existsById(stationId);
    }
}
