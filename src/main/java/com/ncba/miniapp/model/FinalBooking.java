package com.ncba.miniapp.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class FinalBooking extends RequestResponseLog {
    private String offerId;
    private String mblNo;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payment_resp", columnDefinition = "jsonb")
    private JsonNode paymentResp;
}
