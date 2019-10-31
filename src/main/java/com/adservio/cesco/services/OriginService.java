package com.adservio.cesco.services;

import com.adservio.cesco.domain.OriginMission;
import com.adservio.cesco.web.dto.OriginDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OriginService {

    OriginMission createOriginMission(OriginMission originMission);

    OriginMission updateOriginMission(OriginMission originMission);

    Page<OriginMission> findAll(Pageable pageable);

    boolean exist(Long originMissionId);

    void deleteById(Long id);

    void delete(OriginMission t);

}
