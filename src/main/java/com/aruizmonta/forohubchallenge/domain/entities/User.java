package com.aruizmonta.forohubchallenge.domain.entities;

import com.aruizmonta.forohubchallenge.infrastructure.utils.RolePermission;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Size(min = 6, max = 30)
    @Column(nullable = false, unique = true, length = 30)
    private String email;
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String name;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private Set<Role> assignedRoles;

    @OneToMany(targetEntity = Response.class, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Response> responses;

    @OneToMany(targetEntity = Topic.class, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Topic> topics;

    public User() {
        assignedRoles = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (assignedRoles.isEmpty()) return null;
        List<RolePermission> granted = assignedRoles.stream()
                .map(role -> RolePermission.valueOf(role.getName()))
                .toList();
        granted.forEach(rolePermission -> System.out.println(Level.DEBUG + " -> " + rolePermission.name()));


        List<SimpleGrantedAuthority> authorities = granted.stream().map(RolePermission::getPermissions)
                .map(permissions -> permissions.stream().map(permission -> new SimpleGrantedAuthority(permission.name())).toList())
                .flatMap(List::stream)
                .toList();
        authorities.forEach(authority ->
                System.out.println(Level.DEBUG + " -> " + authority.toString())
        );

        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
