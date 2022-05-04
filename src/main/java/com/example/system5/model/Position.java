package com.example.system5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
public class Position extends RepresentationModel<Position> {
    @Column(name = "position_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int position_id;

    @Column(name = "position")
    private String position;

    @OneToOne
    @JoinTable(name = "position_user",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonManagedReference
    public User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "commander_employee",
            joinColumns =
                    { @JoinColumn(name = "commander_position_id")},
        inverseJoinColumns =
                    { @JoinColumn(name = "position_id", referencedColumnName = "position_id")})
    List<Position> employersList;


    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public List<Position> getEmployersList() {
        return employersList;
    }

    public void setEmployersList(List<Position> employersList) {
        this.employersList = employersList;
    }
}
