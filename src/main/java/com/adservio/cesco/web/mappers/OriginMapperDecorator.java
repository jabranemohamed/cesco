package com.adservio.cesco.web.mappers;

import com.adservio.cesco.domain.OriginMission;
import com.adservio.cesco.web.dto.OriginDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

public abstract class OriginMapperDecorator implements OriginMapper {

    private OriginMapper originMapper;

    @Autowired
    public void setMapper(OriginMapper originMapper) {
        this.originMapper = originMapper;
    }

    public OriginMission beerDtoToBeer(OriginDto originDto) {
        OriginMission om = originMapper.originDtoToOrigin(originDto);
        om.setDateDebut(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        om.setActive(true);
        return om;
    }

}
