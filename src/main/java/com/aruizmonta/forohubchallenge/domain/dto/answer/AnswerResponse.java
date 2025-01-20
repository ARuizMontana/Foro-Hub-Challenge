package com.aruizmonta.forohubchallenge.domain.dto.answer;

import com.aruizmonta.forohubchallenge.domain.dto.user.UserResponse;
import com.aruizmonta.forohubchallenge.domain.dto.utils.FormatInstant;
import com.aruizmonta.forohubchallenge.domain.entities.Response;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AnswerResponse implements Serializable {

    private String message;
    private String solution;
    private UserResponse author;
    private String createdAt;

    public AnswerResponse(Response response) {
        message = response.getMessage();
        solution = response.getSolution();
        author = new UserResponse(response.getAuthor());
        createdAt = FormatInstant.toDateTime(response.getCreatedAt());
    }

    public static List<AnswerResponse> toAnswers(List<Response> answers) {
        if (answers == null || answers.isEmpty()) {
            return new ArrayList<>();
        }
        return answers.stream().map(AnswerResponse::new).toList();
    }
}
