package com.ncba.miniapp.model.booking;


import com.ncba.miniapp.model.RequestResponseLog;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
@EqualsAndHashCode(callSuper = true)
public class Payments extends RequestResponseLog {

    @OneToOne(mappedBy = "payments")
    private Booking booking;

    @Column(name = "type")
    private String type;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private String amount;

}
