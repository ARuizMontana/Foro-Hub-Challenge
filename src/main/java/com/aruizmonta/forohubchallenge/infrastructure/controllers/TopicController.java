package com.aruizmonta.forohubchallenge.infrastructure.controllers;

import com.aruizmonta.forohubchallenge.application.services.ITopicService;
import com.aruizmonta.forohubchallenge.domain.dto.topic.TopicRequest;
import com.aruizmonta.forohubchallenge.domain.dto.topic.TopicResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<TopicResponse> findTopicById(@PathVariable Long topicId) {
        Optional<TopicResponse> response = topicService.findTopicById(topicId);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public Page<TopicResponse> findTopicBySearch(
            @RequestParam String name,
            @RequestParam int year,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ) {
        final Page<TopicResponse> topicPages;
        Sort.Direction direction = sort.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, size, direction, "title");
        List<TopicResponse> response = topicService.findTopicBySearch(name, year);
        System.out.println(response);

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), response.size());

        if(CollectionUtils.isEmpty(response)) {
            topicPages = new PageImpl<>(response, pageable, 0);
        } else {

            topicPages = new PageImpl<>(response.subList(start, end), pageable, response.size());
        }

        return topicPages;
    }

    @PostMapping
    public ResponseEntity<TopicResponse> create(@RequestBody @Valid TopicRequest request) {
        TopicResponse response = topicService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<TopicResponse> update(@RequestBody @Valid TopicRequest request, @PathVariable Long topicId) {
        TopicResponse response = topicService.update(request, topicId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<TopicResponse> delete(@PathVariable Long topicId) {
        TopicResponse response = topicService.delete(topicId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
