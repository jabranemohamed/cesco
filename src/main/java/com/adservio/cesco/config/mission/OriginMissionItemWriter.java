package com.adservio.cesco.config.mission;

import com.adservio.cesco.domain.OriginMission;
import com.adservio.cesco.repositories.OriginMissionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OriginMissionItemWriter implements ItemWriter<OriginMission> {

    @Autowired
    private OriginMissionRepository originMissionRepository;

    @Override
    public void write(List<? extends OriginMission> list) throws Exception {
        originMissionRepository.saveAll(list);
    }
}
