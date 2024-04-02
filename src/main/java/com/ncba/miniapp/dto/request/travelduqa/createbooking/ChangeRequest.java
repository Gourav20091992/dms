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
public class ChangeRequest {
    @JsonProperty("type_of_change")
    private String typeOfChange;
    @JsonProperty("change_data")
    private ChangeData changeData;
}