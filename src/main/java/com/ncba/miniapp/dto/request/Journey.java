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
public class Journey {
    @JsonProperty("flight_type")
    private String flightType;
    @JsonProperty("cabin_class")
    private String cabinClass;
    @JsonProperty("depature")
    private String departure;
    private String arrival;
    @JsonProperty("depature_date")
    private String departureDate;
    @JsonProperty("arrival_date")
    private String arrivalDate;
    @JsonProperty("adult_count")
    private Integer adultCount;
    @JsonProperty("child_count")
    private Integer childCount;
    @JsonProperty("infant_count")
    private Integer infantCount;
    private String currency;
}
