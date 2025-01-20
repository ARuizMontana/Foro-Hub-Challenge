package com.aruizmonta.forohubchallenge.infrastructure.repositories.topic;

import com.aruizmonta.forohubchallenge.application.services.ITopicService;
import com.aruizmonta.forohubchallenge.application.services.auth.AuthenticationService;
import com.aruizmonta.forohubchallenge.domain.dto.topic.TopicRequest;
import com.aruizmonta.forohubchallenge.domain.dto.topic.TopicResponse;
import com.aruizmonta.forohubchallenge.domain.entities.Course;
import com.aruizmonta.forohubchallenge.domain.entities.Topic;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import com.aruizmonta.forohubchallenge.infrastructure.repositories.course.CourseRepository;
import com.aruizmonta.forohubchallenge.infrastructure.utils.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicServiceImpl implements ITopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Page<TopicResponse> findAll(Pageable pageable) {
        return topicRepository.findAll(pageable).map(TopicResponse::new);
    }

    @Override
    public List<TopicResponse> findTopicBySearch(String name, int year) {
        System.out.println("Service");
        System.out.println(name);
        System.out.println(year);
        Instant startDate = LocalDateTime.of(year, 1, 1, 0, 0).toInstant(ZoneOffset.UTC);
        Instant endDate = LocalDateTime.of(year, 12, 31, 23, 59, 59).toInstant(ZoneOffset.UTC);
        System.out.println("dates: " + startDate);
        System.out.println("dates: " + endDate);
        return topicRepository.findByTitleAndYear(name, startDate, endDate).stream().map(TopicResponse::new).toList();
    }

    @Override
    public Optional<TopicResponse> findTopicById(Long topicId) {
        return topicRepository.findTopicById(topicId).map(TopicResponse::new);
    }

    @Override
    public TopicResponse create(TopicRequest request) {
        getTopicByMessageAndTitle(request.getMessage(), request.getTitle());

        Topic topic = topicRepository.save(TopicRequest.toEntity(getLoggedInUser(), getCourseById(request.getCourseId()), request));
        return new TopicResponse(topic);
    }

    @Override
    public TopicResponse update(TopicRequest request, Long id) {
        getTopicByMessageAndTitle(request.getMessage(), request.getTitle());

        Optional<Topic> current = topicRepository.findTopicByIdAndAuthor_Id(Long.parseLong(String.valueOf(id)), getLoggedInUser().getId());

        if (current.isEmpty()) {
            throw new EntityNotFoundException("The topic by id " + id + " not belong to the author " + getLoggedInUser().getEmail());
        }
        Topic update = current.get();
        update.setTitle(request.getTitle());
        update.setMessage(request.getMessage());
        update.setCourse(getCourseById(request.getCourseId()));

        return new TopicResponse(topicRepository.save(update));
    }

    @Override
    public TopicResponse delete(Long id) {
        Optional<Topic> current = topicRepository.findTopicByIdAndAuthor_Id(Long.parseLong(String.valueOf(id)), getLoggedInUser().getId());

        if (current.isEmpty()) {
            throw new EntityNotFoundException("The topic by id " + id + " not belong to the author " + getLoggedInUser().getEmail());
        }
        topicRepository.delete(current.get());

        return new TopicResponse(current.get());
    }

    private User getLoggedInUser() {
        return authenticationService.findLoggedInUser();
    }

    private Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found by id : " + id));
    }

    private void getTopicByMessageAndTitle(String message, String title) {
        Optional<Topic> results = topicRepository.findByMessageAndTitleEqualsIgnoreCase(message, title);
        if (results.isPresent()) {
            throw new EntityNotFoundException("The title and topic have already been created previously by the user :" + results.get().getAuthor().getEmail()
                    + " and topic by id: " + results.get().getId());
        }
    }
}
