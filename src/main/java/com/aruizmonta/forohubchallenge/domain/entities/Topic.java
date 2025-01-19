package com.aruizmonta.forohubchallenge.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String title;
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String message;
    //TODO: Check candidate for Enum
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String status;
    @ColumnDefault("now()")
    @Column(name = "created_at",nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @OneToMany(targetEntity = Response.class, mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Response> responses;

    public Topic() {
    }
}
