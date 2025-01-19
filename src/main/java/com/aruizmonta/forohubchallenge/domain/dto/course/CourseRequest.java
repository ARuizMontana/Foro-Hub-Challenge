package com.aruizmonta.forohubchallenge.domain.dto.course;

import com.aruizmonta.forohubchallenge.domain.entities.Course;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourseRequest implements Serializable {
    @NotBlank
    private String name;
    @NotBlank
    private String category;

    public CourseRequest() {
    }

    public CourseRequest(Course course) {
        this.name = course.getName();
        this.category = course.getCategory();
    }

    public Course toEntity() {
        Course course = new Course();
        course.setCategory(category);
        course.setName(name);
        return course;
    }
}
