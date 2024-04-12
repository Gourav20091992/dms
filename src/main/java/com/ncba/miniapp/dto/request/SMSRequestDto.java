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
public class SMSRequestDto {
    @JsonProperty("busRefNo")
    private String busRefNo;
    @JsonProperty("mblNo")
    private String mblNo;
    @JsonProperty("replaceVals")
    private ReplaceValsDto replaceVals;
    @JsonProperty("tmpId")
    private String tmpId;
    @JsonProperty("sysCnl")
    private String sysCnl;
    @JsonProperty("gda")
    private GdaDto gda;
}
