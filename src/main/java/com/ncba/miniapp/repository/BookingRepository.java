package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
