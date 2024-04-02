package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.RequestResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestResponseRepository extends JpaRepository<RequestResponseLog, Long> {

}
