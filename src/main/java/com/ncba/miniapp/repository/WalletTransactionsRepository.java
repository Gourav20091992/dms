package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.WalletTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionsRepository extends JpaRepository<WalletTransactions, Long> {

}
