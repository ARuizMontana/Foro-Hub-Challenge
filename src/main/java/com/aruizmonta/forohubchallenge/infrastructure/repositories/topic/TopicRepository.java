package com.aruizmonta.forohubchallenge.infrastructure.repositories.topic;

import com.aruizmonta.forohubchallenge.domain.entities.Topic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findTopicById(Long id);

    Optional<Topic> findByMessageAndTitleEqualsIgnoreCase(@Size(min = 1, max = 255) @NotBlank String message, @Size(min = 1, max = 255) @NotBlank String title);
}
