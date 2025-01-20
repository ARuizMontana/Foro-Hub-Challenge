package com.aruizmonta.forohubchallenge.application.services;

import com.aruizmonta.forohubchallenge.domain.dto.answer.AnswerRequest;
import com.aruizmonta.forohubchallenge.domain.dto.answer.AnswerResponse;

public interface IAnswerService {
    AnswerResponse create(AnswerRequest  request);
}
