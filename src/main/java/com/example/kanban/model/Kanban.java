package com.example.kanban.model;

import com.example.system5.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "kan_kanban")
@Getter
@Setter
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanban_id", nullable = false)
    private Integer id;

    @Column(name = "kanban_name")
    private String kanbanName;

    @Column(name = "started")
    private Boolean started;

    @Column(name = "continues")
    private Boolean continues;

    @Column(name = "finished")
    private Boolean finished;

    @Column(name = "describe")
    private String describe;

    @OneToOne
    @JoinColumn(name = "author_user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "taskenddate")
    private Date taskEndDate;

    @Column
    private Boolean arch;

    @OneToMany
    @JoinTable(name = "kan_kanban_users",
            joinColumns = {@JoinColumn(name = "kanban_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> userList;

}