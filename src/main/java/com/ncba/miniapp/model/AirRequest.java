package com.ncba.miniapp.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class AirRequest extends RequestResponseLog {
    private String filter;
    private String value;
}
