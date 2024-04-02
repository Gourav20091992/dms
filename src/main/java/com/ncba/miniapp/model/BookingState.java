package com.ncba.miniapp.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class BookingState extends RequestResponseLog {
    private String bookingId;
    private String type;
    private String currency;
    private String amount;

}
