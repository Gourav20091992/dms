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
public class GdaDto {
    @JsonProperty("busCnl")
    private String busCnl;
    @JsonProperty("jrnNo")
    private String jrnNo;
}
