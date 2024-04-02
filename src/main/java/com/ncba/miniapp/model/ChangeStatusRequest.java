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
public class ChangeStatusRequest extends RequestResponseLog {
    @JsonProperty("change_id")
    private String changeId;
}
