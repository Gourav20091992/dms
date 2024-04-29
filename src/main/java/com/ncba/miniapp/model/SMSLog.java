package com.ncba.miniapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class SMSLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sixDigitNo;
    private String busRefNo;
    private String mblNo;
    private long generationTime;
    @CreationTimestamp
    @Basic
    @Column(updatable = false)
    private LocalDateTime createdDate;
    private String verifiedStatus;
}
