package com.ncba.miniapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class FinalBooking extends RequestResponseLog {
    private String offerId;
    private String mblNo;
    @Lob
    @Column(name = "payment_resp", columnDefinition = "TEXT")
    private String paymentResp;
}
