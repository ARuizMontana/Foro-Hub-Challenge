package com.aruizmonta.forohubchallenge.domain.dto.topic;


import com.aruizmonta.forohubchallenge.domain.entities.Course;
import com.aruizmonta.forohubchallenge.domain.entities.Topic;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class TopicRequest implements Serializable {
    @NotBlank
    @Size(min = 1, max = 255)
    private String title;
    @NotBlank
    @Size(min = 1, max = 255)
    private String message;
    @Positive
    private Long courseId;


    public static Topic toEntity(User user, Course course, TopicRequest request) {
        Topic topic = new Topic();
        topic.setTitle(request.title);
        topic.setMessage(request.message);
        topic.setStatus("Open");
        topic.setCreatedAt(Instant.now());
        topic.setAuthor(user);
        topic.setCourse(course);

        return topic;
    }
}
