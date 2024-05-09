package com.ncba.miniapp.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FinalBookingResponse {
    private String offerId;
    private String mblNo;
    private JsonNode paymentResp;
}
