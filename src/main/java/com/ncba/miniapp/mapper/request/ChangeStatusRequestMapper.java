package com.ncba.miniapp.mapper.request;

import com.ncba.miniapp.dto.request.ChangeStatusRequestDto;
import com.ncba.miniapp.model.ChangeStatusRequest;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusRequestMapper {
    public ChangeStatusRequest mapToEntity(ChangeStatusRequestDto dto) {
        ChangeStatusRequest entity = new ChangeStatusRequest();
        entity.setChangeId(dto.getChangeId());
        return entity;
    }
}
