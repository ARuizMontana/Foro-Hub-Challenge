package com.aruizmonta.forohubchallenge.infrastructure.repositories.course;

import com.aruizmonta.forohubchallenge.domain.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course, Long> {
}
