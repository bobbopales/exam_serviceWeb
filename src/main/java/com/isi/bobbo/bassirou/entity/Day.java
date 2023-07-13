package com.isi.bobbo.bassirou.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String searchDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    private List<SearchItem> searchItems = new ArrayList<>();;

    public Day(LocalDate date) {
        this.date=date;
    }
}
