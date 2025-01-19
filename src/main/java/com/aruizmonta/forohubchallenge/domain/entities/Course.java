package com.aruizmonta.forohubchallenge.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String name;
    //TODO: Check candidate for Enum
    @Size(min = 1, max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String category;

    @OneToMany(targetEntity = Topic.class, mappedBy = "course", cascade = CascadeType.ALL)
    private List<Topic> topics;

    public Course() {
    }
}
