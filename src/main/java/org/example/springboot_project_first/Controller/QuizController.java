package org.example.springboot_project_first.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.springboot_project_first.dto.ScoreResponse;
import org.example.springboot_project_first.model.Response;
import org.example.springboot_project_first.dto.QuestionWrapper;
import org.example.springboot_project_first.services.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@Tag(name = "Quiz Controller", description = "Api related to the quiz")
public class QuizController {

    private QuizService quizService;

    // constructor injection :
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Operation(summary = "Create a new quiz", description = "Create a new quiz by passing category, number of questions to include in the quiz, title of the quiz")
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noOfQ, @RequestParam String title) {
        return quizService.createQuiz(category, noOfQ, title);
    }

    // get the quiz by its id(quiz number) :
    @Operation(summary = "Get the quiz by its id", description = "Pass the id of the quiz you want to fetch(quiz number)")
    @GetMapping("id/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer id) {
        return quizService.getQuizById(id);
    }

    // to calculate the result(number of correct answers submitted by the user, we use response(self made) class for this :
    @Operation(summary = "Submit the quiz", description = "Submit your answers by passing the quiz id, and your responses should include the question id you attempted and the answer you want to submit for that question")
    @PostMapping("submit/{id}")
    public ResponseEntity<ScoreResponse> submitQuiz(@PathVariable Integer id, @RequestBody @Valid List<Response> response) {
        return quizService.calculateScore(id, response);
    }
}
