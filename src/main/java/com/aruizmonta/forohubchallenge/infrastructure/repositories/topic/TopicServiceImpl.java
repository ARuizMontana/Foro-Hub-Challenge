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
    public Optional<TopicResponse> findTopicById(Long topicId) {
        return topicRepository.findTopicById(topicId).map(TopicResponse::new);
    }

    @Override
    public TopicResponse create(TopicRequest request) {
        User user = authenticationService.findLoggedInUser();
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found by id : "+request.getCourseId()));
        Optional<Topic> results = topicRepository.findByMessageAndTitleEqualsIgnoreCase(request.getMessage(), request.getTitle());
        if(results.isPresent()) {
            throw new EntityNotFoundException("The title and topic have already been created previously by the user :" +results.get().getAuthor().getEmail()
                    +" and topic by id: " + results.get().getId());
        }
        Topic topic = topicRepository.save(TopicRequest.toEntity(user, course, request));
        return new TopicResponse(topic);
    }
}
