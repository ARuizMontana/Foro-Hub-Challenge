package com.aruizmonta.forohubchallenge.infrastructure.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public enum RolePermission {
    admin(Arrays.asList(
            Permission.READ_MY_PROFILE,
            Permission.READ_ALL_COURSES,
            Permission.READ_ONE_COURSE
    )),
    user(Arrays.asList(
            Permission.READ_MY_PROFILE,
            Permission.READ_ALL_COURSES,
            Permission.READ_ONE_COURSE
    )),
    moderator(Arrays.asList(
            Permission.READ_MY_PROFILE,
            Permission.READ_ALL_COURSES,
            Permission.READ_ONE_COURSE
    )),
    reader(Arrays.asList(
            Permission.READ_MY_PROFILE,
            Permission.READ_ALL_COURSES,
            Permission.READ_ONE_COURSE
    ));

    private List<Permission> permissions;

    RolePermission(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
