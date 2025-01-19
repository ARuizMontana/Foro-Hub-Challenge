package com.aruizmonta.forohubchallenge.domain.dto.course;

import com.aruizmonta.forohubchallenge.domain.entities.Course;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {

    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String category;

    public CourseResponse() {
    }

    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.category = course.getCategory();
    }
}
