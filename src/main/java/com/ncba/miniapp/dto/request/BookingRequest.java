package com.ncba.miniapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    @JsonProperty("result_id")
    private String resultId;
    @JsonProperty("offer_id")
    private String offerId;
    private List<Passenger> passengersList;
    private Payments payments;
}
