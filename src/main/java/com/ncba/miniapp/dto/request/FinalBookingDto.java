package com.ncba.miniapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FinalBookingDto {
    private String offerId;
    private String mblNo;
    private Map<String, Object> paymentResp;
}
