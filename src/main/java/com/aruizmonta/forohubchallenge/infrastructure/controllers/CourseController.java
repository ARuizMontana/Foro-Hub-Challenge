package com.aruizmonta.forohubchallenge.infrastructure.controllers;

import com.aruizmonta.forohubchallenge.application.services.ICourseService;
import com.aruizmonta.forohubchallenge.domain.dto.course.CourseRequest;
import com.aruizmonta.forohubchallenge.domain.dto.course.CourseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> findAll(Pageable pageable) {
        Page<CourseResponse> coursesPage = courseService.findAll(pageable);
        if (coursesPage.hasContent()) {
            return ResponseEntity.ok(coursesPage);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> findById(@PathVariable Long courseId) {
        Optional<CourseResponse> coursesPage = courseService.findById(courseId);
        return coursesPage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody @Valid CourseRequest course) {
        CourseResponse newCourse = courseService.create(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponse> update(@PathVariable Long courseId, @RequestBody @Valid CourseRequest course) {
        CourseResponse updateCourse = courseService.updateById(courseId, course);
        return ResponseEntity.ok(updateCourse);
    }
}
