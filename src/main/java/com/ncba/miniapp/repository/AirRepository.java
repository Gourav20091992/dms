package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.AirRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirRepository extends JpaRepository<AirRequest, Long> {

}
