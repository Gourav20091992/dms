package com.ncba.miniapp.dto.request.travelduqa.createbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangeData {
    @JsonProperty("new_airline")
    private String newAirline;
    @JsonProperty("new_cabin_class")
    private String newCabinClass;
    @JsonProperty("departure_trip")
    private DepartureTrip departureTrip;
    @JsonProperty("return_trip")
    private ReturnTrip returnTrip;
    @JsonProperty("passenger_details")
    private List<PassengerDetail> passengerDetails;
    @JsonProperty("remove_passengers")
    private List<RemovePassenger> removePassengers;
    @JsonProperty("add_passengers")
    private List<AddPassenger> addPassengers;
}