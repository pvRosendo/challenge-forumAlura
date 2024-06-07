package com.rosendo.forumAlura.domain.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="tb_authors")
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<TopicModel> topics;
}
