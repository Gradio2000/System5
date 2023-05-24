package com.example.priziv.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "p_priziv_month_year")
@Getter
@Setter
public class PrizivMonthYear {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "month_year_name")
    private String monthYearName;

    @OneToMany(targetEntity = Priziv.class, cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "monthYearId")
    private List<Priziv> prizivList;
}
