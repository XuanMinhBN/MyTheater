package org.xumin.mytheater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name = "movie_name_vn")
    private String movieNameVn;

    @Column(name = "movie_name_eng")
    private String movieNameEng;

    @Column(name = "content")
    private String content;

    @Column(name = "director")
    private String director;

    @Column(name = "actor")
    private String actor;

    @Column(name = "movie_production_company")
    private String productionCompany;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "rated")
    private int rated;

    @Column(name = "large_image")
    private String largeImage;

    @Column(name = "small_image")
    private String smallImage;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
    private MovieTrailer movieTrailer;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MovieVersion> movieVersionList;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<RoomSchedule> roomScheduleList;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genres",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "genres_id")}
    )
    private Set<Genres> genres;
}
