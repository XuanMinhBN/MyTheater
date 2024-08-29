package org.xumin.mytheater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_name")
    private String seatName;

    @Column(name = "seat_status")
    private int seatStatus;

    @Column(name = "seat_type")
    private int seatType;

    @ManyToMany(mappedBy = "seats")
    private Set<Ticket> tickets;
}
