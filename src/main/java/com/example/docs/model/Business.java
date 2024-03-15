package com.example.docs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "d_business")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Business {

    @Id
    @Column(name = "business_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer businessId;

    @Column(name = "business_name")
    private String businessName;

    @OneToMany(targetEntity = Docs.class, cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private List<Docs> docsList;
}
