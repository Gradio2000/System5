package com.example.pdd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pdd_answer")
@Getter
@Setter
public class PddAnswer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "pdd_answer_name")
    private String pddAnswerName;

    @Column(name = "pdd_ques_id")
    private Integer pddQuesId;

    @Column(name = "istrue")
    private Boolean istrue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PddAnswer pddAnswer = (PddAnswer) o;
        return id == pddAnswer.id && Objects.equals(pddAnswerName, pddAnswer.pddAnswerName) && Objects.equals(pddQuesId, pddAnswer.pddQuesId) && Objects.equals(istrue, pddAnswer.istrue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pddAnswerName, pddQuesId, istrue);
    }
}
