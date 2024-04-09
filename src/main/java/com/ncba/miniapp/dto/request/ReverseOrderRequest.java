package com.ncba.miniapp.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReverseOrderRequest {
    private String appId;
    private String orderNo;
    private String orderDate;
    private String secretKey;
}
