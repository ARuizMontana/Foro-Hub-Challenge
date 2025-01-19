package com.aruizmonta.forohubchallenge.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private Long id;
    @NonNull
    @Size(min = 3, max = 255)
    @Column(name = "message", nullable = false)
    private String message;
    @NonNull
    @Size(min = 3, max = 255)
    @Column(name = "solution", nullable = false)
    private String solution;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
    private Topic topic;

    public Response() {
    }
}
