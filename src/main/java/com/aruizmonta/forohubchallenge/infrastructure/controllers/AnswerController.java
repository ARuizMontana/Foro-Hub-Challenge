package com.aruizmonta.forohubchallenge.infrastructure.controllers;

import com.aruizmonta.forohubchallenge.application.services.IAnswerService;
import com.aruizmonta.forohubchallenge.domain.dto.answer.AnswerRequest;
import com.aruizmonta.forohubchallenge.domain.dto.answer.AnswerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private IAnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerResponse> create(@RequestBody @Valid AnswerRequest request) {
        AnswerResponse response = answerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
