package com.rosendo.forumAlura.domain.models;

import com.rosendo.forumAlura.domain.enums.EnumCourse;
import com.rosendo.forumAlura.domain.enums.EnumState;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="tb_topic")
public class TopicModel implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime dateCreation;
    private EnumState topicState;
    private EnumCourse course;

    @ManyToOne
    private AuthorModel author;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicModel that = (TopicModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
