package com.ncba.miniapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReverseOrderStatus {
    private String appId;
    private String reverseOrderNo;
    private String reverseOrderDate;
}
