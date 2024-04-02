package com.ncba.miniapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class SelectOfferRequest extends RequestResponseLog {
    @JsonProperty("result_id")
    private String resultId;
    @JsonProperty("offer_id")
    private String offerId;
}