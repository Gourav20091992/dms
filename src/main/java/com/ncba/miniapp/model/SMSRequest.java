package com.ncba.miniapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class SMSRequest extends RequestResponseLog {
    @JsonProperty("busRefNo")
    private String busRefNo;
    @JsonProperty("mblNo")
    private String mblNo;
    @JsonProperty("replaceVals")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "replace_vals_id")
    private ReplaceVals replaceVals;
    @JsonProperty("tmpId")
    private String tmpId;
    @JsonProperty("sysCnl")
    private String sysCnl;
    @JsonProperty("gda")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gda_id")
    private Gda gda;
}
