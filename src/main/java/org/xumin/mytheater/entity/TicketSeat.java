package org.xumin.mytheater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket_seat")
public class TicketSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_seat_id")
    private Long ticketSeatId;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "seat_id")
    private Long seatId;
}
