package com.adservio.cesco.config.typeMission;

import com.adservio.cesco.domain.TypeMission;
import com.adservio.cesco.repositories.TypeMissionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeMissionItemWriter implements ItemWriter<TypeMission> {

    @Autowired
    private TypeMissionRepository typeMissionRepository;

    @Override
    public void write(List<? extends TypeMission> list) throws Exception {
        typeMissionRepository.saveAll(list);
    }
}
