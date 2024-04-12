package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.SMSLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SMSLogRepository extends JpaRepository<SMSLog, Long> {
    public SMSLog findFirstByBusRefNoAndMblNoOrderByCreatedDateDesc(String busRefNo, String mblNo);
}
