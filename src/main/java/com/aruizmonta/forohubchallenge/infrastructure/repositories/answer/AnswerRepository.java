package com.aruizmonta.forohubchallenge.infrastructure.repositories.answer;

import com.aruizmonta.forohubchallenge.domain.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Response, Long> {
}
