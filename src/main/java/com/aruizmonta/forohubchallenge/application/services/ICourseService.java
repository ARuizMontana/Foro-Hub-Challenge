package com.aruizmonta.forohubchallenge.application.services;

import com.aruizmonta.forohubchallenge.domain.dto.course.CourseRequest;
import com.aruizmonta.forohubchallenge.domain.dto.course.CourseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICourseService {
    Page<CourseResponse> findAll(Pageable pageable);
    Optional<CourseResponse> findById(Long courseId);
    CourseResponse create(CourseRequest course);
    CourseResponse updateById(Long courseId, CourseRequest course);
}
