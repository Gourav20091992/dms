package com.ncba.miniapp.dto.request.travelduqa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private String id;
    private Payments payments;
}
