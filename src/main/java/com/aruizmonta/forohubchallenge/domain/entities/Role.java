package com.aruizmonta.forohubchallenge.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @JsonIgnoreProperties({"roles","handler","hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "assignedRoles")
    private Set<User> users;

    public Role() {
        users = new HashSet<>();
    }


}
