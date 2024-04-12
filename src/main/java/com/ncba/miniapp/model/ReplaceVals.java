package com.ncba.miniapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class ReplaceVals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Basic
    @Column(name = "CREATE_DATE", updatable = false)
    private Timestamp createdDate;

    @JsonProperty("ntfDesc")
    private String ntfDesc;

    @OneToOne(mappedBy = "replaceVals")
    private SMSRequest smsRequest;
}
