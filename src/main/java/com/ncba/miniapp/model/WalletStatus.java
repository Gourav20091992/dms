package com.ncba.miniapp.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class WalletStatus extends RequestResponseLog {
    private String walletStatusId;
}
