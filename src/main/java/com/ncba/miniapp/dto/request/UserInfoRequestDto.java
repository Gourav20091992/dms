package com.ncba.miniapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequestDto {
    private String accessToken;
    private String appKey;
    private String appSecret;
    private String openId;
}
