package com.ncba.miniapp.model.booking;

import com.ncba.miniapp.model.RequestResponseLog;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "booking")
public class Booking extends RequestResponseLog {
    @Column(name = "result_id")
    private String resultId;

    @Column(name = "offer_id")
    private String offerId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private List<Passenger> passengers = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payments payments;

}
