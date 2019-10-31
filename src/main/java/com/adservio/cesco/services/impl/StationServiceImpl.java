package com.adservio.cesco.services.impl;

import com.adservio.cesco.domain.Station;
import com.adservio.cesco.services.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class StationServiceImpl implements StationService {
    @Override
    public Station createStation(Station originMission) {
        return null;
    }

    @Override
    public Station updateStation(Station originMission) {
        return null;
    }

    @Override
    public Page<Station> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Station t) {

    }
}
