package com.aruizmonta.forohubchallenge.domain.dto.course;

import com.aruizmonta.forohubchallenge.domain.entities.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourseRequest implements Serializable {
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @NotBlank
    @Size(min = 1, max = 50)
    private String category;

    public Course toEntity() {
        Course course = new Course();
        course.setCategory(category);
        course.setName(name);
        return course;
    }
}
