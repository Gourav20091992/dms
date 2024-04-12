package com.ncba.miniapp.model;

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
public class Gda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Basic
    @Column(name = "CREATE_DATE", updatable = false)
    private Timestamp createdDate;
    private String busCnl;
    private String jrnNo;

    @OneToOne(mappedBy = "gda")
    private SMSRequest smsRequest;
}
