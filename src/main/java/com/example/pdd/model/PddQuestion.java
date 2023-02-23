package com.example.pdd.model;

import com.example.qtest.model.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pdd_question")
@Getter
@Setter
public class PddQuestion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "pdd_question_name")
    private String pddQuestionName;

    @Column(name = "image_ref")
    private String imageRef;

    @Column(name = "promt")
    private String promt;

    @Column(name = "bilet_number")
    private Integer biletNumber;

    @OneToMany(targetEntity = PddAnswer.class, fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, mappedBy = "pddQuesId")
    private List<Answer> pddAnswerList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PddQuestion that = (PddQuestion) o;

        if (id != that.id) return false;
        if (!Objects.equals(pddQuestionName, that.pddQuestionName))
            return false;
        if (!Objects.equals(imageRef, that.imageRef)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pddQuestionName != null ? pddQuestionName.hashCode() : 0);
        result = 31 * result + (imageRef != null ? imageRef.hashCode() : 0);
        return result;
    }
}
