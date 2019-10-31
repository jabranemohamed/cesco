package com.adservio.cesco.web.mappers;

import com.adservio.cesco.domain.OriginMission;
import com.adservio.cesco.web.dto.OriginDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
@DecoratedWith(OriginMapperDecorator.class)
public interface OriginMapper {

    OriginDto originToOriginDto(OriginMission originMission);

    OriginMission originDtoToOrigin(OriginDto dto);
}
