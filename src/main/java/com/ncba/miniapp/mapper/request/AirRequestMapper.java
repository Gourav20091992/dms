package com.ncba.miniapp.mapper.request;

import com.ncba.miniapp.dto.request.AirRequestDto;
import com.ncba.miniapp.model.AirRequest;
import org.springframework.stereotype.Component;

@Component
public class AirRequestMapper {
    public AirRequest mapToEntity(AirRequestDto dto) {
        AirRequest entity = new AirRequest();
        entity.setFilter(dto.getFilter());
        entity.setValue(dto.getValue());
        return entity;
    }
}
