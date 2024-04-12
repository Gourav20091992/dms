package com.ncba.miniapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class SMSTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String templateId;
    private String templateMsg;
    @CreationTimestamp
    @Basic
    @Column(name = "CREATE_DATE", updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    @Basic
    @Column(name = "UPDATE_DATE", insertable = false)
    private Timestamp updateDate;
    @Basic
    @Column(name = "VERSION")
    private Integer version;

    @PreUpdate
    public void preUpdate() {
        this.version = this.version != null ? this.version + 1 : 1;
    }
}