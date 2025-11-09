package org.example.springboot_project_first.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponse {

    // total number of correct answers
    private int score;

    // number of questions in the quiz
    private int totalQuestions;
}
