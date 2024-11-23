package org.xumin.mytheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.xumin.mytheater.entity.TicketSeat;

@Repository
public interface TicketSeatRepository extends JpaRepository<TicketSeat, Long> {
}
