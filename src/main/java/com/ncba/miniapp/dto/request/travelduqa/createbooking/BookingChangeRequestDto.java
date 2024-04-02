package com.ncba.miniapp.dto.request.travelduqa.createbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookingChangeRequestDto {
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("change_request")
    private ChangeRequest changeRequest;
}
