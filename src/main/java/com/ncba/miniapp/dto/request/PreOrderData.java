package com.ncba.miniapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PreOrderData {
    private String appId;
    private String merchantId;
    private String totalAmount;
    private String goodsDesc;
    private String accessToken;
    private String orderNo;
    private String orderDate;
    private String orderTime;
    private String goodsName;
    private String appVersion;
    private String productCategory;
    private String remark;
    private String validUnit;
    private String validNum;
    private String channelName;
    private String notifyUrl;
    private String chargeCategory;
    private String secretKey;

}
