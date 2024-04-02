package com.ncba.miniapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ncba.miniapp.dto.request.travelduqa.createbooking.ChangeRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class BookingChangeRequest extends RequestResponseLog {
    @JsonProperty("order_id")
    private String orderId;
    @Transient
    @JsonProperty("change_request")
    private ChangeRequest changeRequest;
}
