package com.aruizmonta.forohubchallenge.infrastructure.repositories.answer;

import com.aruizmonta.forohubchallenge.application.services.IAnswerService;
import com.aruizmonta.forohubchallenge.application.services.auth.AuthenticationService;
import com.aruizmonta.forohubchallenge.domain.dto.answer.AnswerRequest;
import com.aruizmonta.forohubchallenge.domain.dto.answer.AnswerResponse;
import com.aruizmonta.forohubchallenge.domain.entities.Response;
import com.aruizmonta.forohubchallenge.domain.entities.Topic;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import com.aruizmonta.forohubchallenge.infrastructure.repositories.topic.TopicRepository;
import com.aruizmonta.forohubchallenge.infrastructure.utils.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AnswerServiceImpl implements IAnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public AnswerResponse create(AnswerRequest request) {
        User user = authenticationService.findLoggedInUser();
        Topic topic = topicRepository.findTopicById(request.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found by id : "+request.getTopicId()));
        Response answer = answerRepository.save(AnswerRequest.toEntity(user, topic, request));
        return new AnswerResponse(answer);
    }
}
