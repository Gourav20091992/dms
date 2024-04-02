package com.ncba.miniapp.mapper.request;

import com.ncba.miniapp.dto.request.travelduqa.createbooking.BookingChangeRequestDto;
import com.ncba.miniapp.model.BookingChangeRequest;
import org.springframework.stereotype.Component;

@Component
public class BookingChangeRequestMapper {
    public BookingChangeRequest mapToEntity(BookingChangeRequestDto dto) {
        BookingChangeRequest entity = new BookingChangeRequest();
        entity.setChangeRequest(dto.getChangeRequest());
        entity.setOrderId(dto.getOrderId());
        return entity;
    }
}
