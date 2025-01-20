package com.aruizmonta.forohubchallenge.domain.dto.answer;

import com.aruizmonta.forohubchallenge.domain.entities.Response;
import com.aruizmonta.forohubchallenge.domain.entities.Topic;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class AnswerRequest implements Serializable {
    @NotBlank
    @Size(min = 3, max = 255)
    private String message;
    @NotBlank
    @Size(min = 3, max = 255)
    private String solution;
    @NotBlank
    private Long topicId;

    public AnswerRequest() {

    }

    public static Response toEntity(User author, Topic topic, AnswerRequest request) {
        Response answer = new Response();
        answer.setMessage(request.message);
        answer.setSolution(request.solution);
        answer.setCreatedAt(Instant.now());
        answer.setAuthor(author);
        answer.setTopic(topic);
        return answer;
    }

}
