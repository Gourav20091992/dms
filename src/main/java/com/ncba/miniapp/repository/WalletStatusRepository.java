package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.WalletStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletStatusRepository extends JpaRepository<WalletStatus, Long> {

}
