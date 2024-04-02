package com.ncba.miniapp.dto.request.travelduqa.createbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddPassenger {
    private String title;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    private String gender;
    private String type;
    @JsonProperty("born_on")
    private String bornOn;
    @JsonProperty("phone_code")
    private String phoneCode;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
}