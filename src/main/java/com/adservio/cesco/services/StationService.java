package com.adservio.cesco.services;

import com.adservio.cesco.domain.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StationService {
    Station createStation(Station originMission);

    Station updateStation(Station originMission);

    Page<Station> findAll(Pageable pageable);

    void deleteById(Long id);

    void delete(Station t);
}
