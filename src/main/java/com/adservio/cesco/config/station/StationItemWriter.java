package com.adservio.cesco.config.station;

import com.adservio.cesco.domain.Station;
import com.adservio.cesco.repositories.StationRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationItemWriter implements ItemWriter<Station> {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public void write(List<? extends Station> list) throws Exception {
       stationRepository.saveAll(list);
    }
}
