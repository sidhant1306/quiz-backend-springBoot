package org.example.springboot_project_first.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Integer id;
    @NotBlank(message = "Submitted answer can not be blank")
    private String submittedAnswer;
    @NotNull(message = "question id cannot be null")
    private Integer questionId;     // this is the id of question that the user is answering
}
