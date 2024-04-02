package com.ncba.miniapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {
    private String version;
    private String signature;
    private String signType;
    private String service;
    private String format;
    private String requestId;
    private String merchantId;
    private String appId;
    private String requestTime;
    private String orderNo;
    private String orderDate;
    private String tradeNo;
    private String orderStatus;
    private String totalAmount;
}
