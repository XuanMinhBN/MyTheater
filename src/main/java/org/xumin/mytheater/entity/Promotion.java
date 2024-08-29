package org.xumin.mytheater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    @Column(name = "promotion_name")
    private String promotionName;

    @Column(name = "discount_level")
    private int discountLevel;

    @Column(name = "detail")
    private String detail;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end-date")
    private LocalDate endDate;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<Invoice> invoices;
}
