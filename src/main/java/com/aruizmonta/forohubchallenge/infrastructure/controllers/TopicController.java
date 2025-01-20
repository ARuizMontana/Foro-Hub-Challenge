package com.aruizmonta.forohubchallenge.infrastructure.controllers;

import com.aruizmonta.forohubchallenge.application.services.ITopicService;
import com.aruizmonta.forohubchallenge.domain.dto.topic.TopicRequest;
import com.aruizmonta.forohubchallenge.domain.dto.topic.TopicResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private ITopicService topicService;

    @GetMapping
    public Page<TopicResponse> findAll(Pageable pageable) {
        return topicService.findAll(pageable);
    }

    @GetMapping("/{topicId}")
    public ResponseEntity<TopicResponse> findTopicById(@PathVariable @Valid Long topicId) {
        Optional<TopicResponse> response = topicService.findTopicById(topicId);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TopicResponse> create(@RequestBody @Valid TopicRequest request) {
        TopicResponse response = topicService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
