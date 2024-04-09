package com.ncba.miniapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryRequest {
    private String appId;
    private String merchantId;
    private String channelName;
    private String orderNo;
    private String orderDate;
    private String tradeNo;
    private String secretKey;
}