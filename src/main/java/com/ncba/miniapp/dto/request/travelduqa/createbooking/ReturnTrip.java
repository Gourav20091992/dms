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
public class ReturnTrip {
    private String from;
    private String to;
    @JsonProperty("new_date")
    private String newDate;
    @JsonProperty("new_time")
    private String newTime;
}