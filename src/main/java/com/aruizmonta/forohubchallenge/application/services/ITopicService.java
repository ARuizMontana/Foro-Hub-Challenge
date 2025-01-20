package com.aruizmonta.forohubchallenge.application.services;

import com.aruizmonta.forohubchallenge.domain.dto.topic.TopicRequest;
import com.aruizmonta.forohubchallenge.domain.dto.topic.TopicResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ITopicService {
    Page<TopicResponse> findAll(Pageable pageable);

    Optional<TopicResponse> findTopicById(Long topicId);

    TopicResponse create(TopicRequest request);
}
