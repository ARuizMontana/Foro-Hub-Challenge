package com.aruizmonta.forohubchallenge.infrastructure.repositories.topic;

import com.aruizmonta.forohubchallenge.domain.entities.Topic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findTopicById(Long id);

    Optional<Topic> findTopicByIdAndAuthor_Id(Long id, Long authorId);

    Optional<Topic> findByMessageAndTitleEqualsIgnoreCase(@Size(min = 1, max = 255) @NotBlank String message, @Size(min = 1, max = 255) @NotBlank String title);

    @Query("SELECT t FROM Topic t WHERE t.title LIKE %:title% AND t.createdAt BETWEEN :startDate AND :endDate")
    List<Topic> findByTitleAndYear(@Param("title") String title, @Param("startDate") Instant startDate, @Param("endDate") Instant endDate);
}
