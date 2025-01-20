package com.aruizmonta.forohubchallenge.infrastructure.repositories.course;

import com.aruizmonta.forohubchallenge.application.services.ICourseService;
import com.aruizmonta.forohubchallenge.domain.dto.course.CourseRequest;
import com.aruizmonta.forohubchallenge.domain.dto.course.CourseResponse;
import com.aruizmonta.forohubchallenge.domain.entities.Course;
import com.aruizmonta.forohubchallenge.infrastructure.utils.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Page<CourseResponse> findAll(Pageable pageable) {
        return  courseRepository.findAll(pageable).map(CourseResponse::new);
    }

    @Override
    public Optional<CourseResponse> findById(Long courseId) {
        return courseRepository.findById(courseId).map(CourseResponse::new);
    }

    @Override
    public CourseResponse create(CourseRequest course) {
        Course newCourse = courseRepository.save(course.toEntity());
        return new CourseResponse(newCourse);
    }

    @Override
    public CourseResponse updateById(Long courseId, CourseRequest course) {
        Course search = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found by id : "+courseId));

        search.setName(course.getName());
        search.setCategory(course.getCategory());

        courseRepository.save(search);

        return new CourseResponse(search);
    }
}
