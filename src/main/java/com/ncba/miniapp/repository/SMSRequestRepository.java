package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.SMSRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SMSRequestRepository extends JpaRepository<SMSRequest, Long> {
}
