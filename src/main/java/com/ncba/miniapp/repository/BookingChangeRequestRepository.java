package com.ncba.miniapp.repository;

import com.ncba.miniapp.model.BookingChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingChangeRequestRepository extends JpaRepository<BookingChangeRequest, Long> {

}
