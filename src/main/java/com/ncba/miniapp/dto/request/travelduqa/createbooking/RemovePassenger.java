package com.ncba.miniapp.dto.request.travelduqa.createbooking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RemovePassenger {
    private Passenger passenger;
}