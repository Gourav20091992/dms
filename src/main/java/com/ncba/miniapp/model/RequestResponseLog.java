package com.ncba.miniapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
@MappedSuperclass
public class RequestResponseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestUrl;
    @Lob
    private String requestBody;
    private int responseStatus;
    @Lob
    private String responseBody;
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