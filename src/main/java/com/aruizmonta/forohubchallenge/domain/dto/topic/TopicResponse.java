package com.aruizmonta.forohubchallenge.domain.dto.topic;

import com.aruizmonta.forohubchallenge.domain.dto.answer.AnswerResponse;
import com.aruizmonta.forohubchallenge.domain.dto.course.CourseResponse;
import com.aruizmonta.forohubchallenge.domain.dto.user.UserResponse;
import com.aruizmonta.forohubchallenge.domain.dto.utils.FormatInstant;
import com.aruizmonta.forohubchallenge.domain.entities.Topic;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TopicResponse implements Serializable {
    private Long id;
    private String title;
    private String message;
    private String status;
    private String createdAt;
    private UserResponse author;
    private CourseResponse course;
    private List<AnswerResponse> answers;

    public TopicResponse(Topic topic) {
        id = topic.getId();
        title = topic.getTitle();
        message = topic.getMessage();
        status = topic.getStatus();
        createdAt = FormatInstant.toDateTime(topic.getCreatedAt());
        author = new UserResponse(topic.getAuthor());
        course = new CourseResponse(topic.getCourse());
        answers = AnswerResponse.toAnswers(topic.getResponses());
    }
}
