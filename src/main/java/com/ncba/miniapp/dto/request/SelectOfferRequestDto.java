package com.ncba.miniapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SelectOfferRequestDto {
    @JsonProperty("result_id")
    private String resultId;
    @JsonProperty("offer_id")
    private String offerId;
}