package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.FinalBooking;
import com.ncba.miniapp.model.FinalBookingProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinalBookingRepository extends JpaRepository<FinalBooking, Long> {
    List<FinalBookingProjection> findByMblNo(String mblNo);

    List<FinalBooking> findTop10ByMblNoOrderByCreatedDateDesc(String mblNo);
}
