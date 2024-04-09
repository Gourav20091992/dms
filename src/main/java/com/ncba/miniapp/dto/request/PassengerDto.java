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
public class PassengerDto {
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("phone_code")
    private String phoneCode;
    private String email;
    @JsonProperty("born_on")
    private String bornOn;
    private String title;
    private String gender;
    @JsonProperty("family_name")
    private String familyName;
    @JsonProperty("given_name")
    private String givenName;
    private String type;

}
