package org.xumin.mytheater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long id;

    @Column(name = "booking_date")
    private LocalTime bookingDate;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "total_money")
    private double totalMoney;

    @Column(name = "seat")
    private String seat;

    @Column(name = "promotion_name")
    private String promotionName;

    @Column(name = "watch_movie_day")
    private LocalDate watchMovieDay;

    @Column(name = "watch_movie_time")
    private String watchMovieTime;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id")
    private Promotion promotion;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> ticketList;
}
