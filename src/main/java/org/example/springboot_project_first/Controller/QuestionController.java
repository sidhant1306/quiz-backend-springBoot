package org.example.springboot_project_first.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.springboot_project_first.model.Question;
import org.example.springboot_project_first.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Question Controller", description = "Api related to questions")    // used in naming display of swagger.
public class QuestionController {
   private QuestionService qService;

   // constructor injection of qService class :
   public QuestionController(QuestionService qService) {
       this.qService = qService;
   }

    @Operation(summary = "Get all questions", description = "Fetches all questions from the database")   // shown on swagger display screen
    @GetMapping("/AllQuestions")
   public ResponseEntity<List<Question>> getAllQuestions() {
       return qService.getAllQuestions();
   }

   @Operation(summary = "Get questions by category", description = "Fetches questions based on category")
   @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllByCategory(@PathVariable String category) {
       return qService.getAllQuestionsByCategory(category);
   }
   @Operation(summary = "Get question by id", description = "Fetches question based on id")
   @GetMapping("/AllQuestions/id/{id}")
    public Question getById(@PathVariable int id) {
       return qService.getQuestionById(id);
   }

    @Operation(summary = "Get questions by difficultyLevel", description = "Fetches questions based on difficultyLevel")
   @GetMapping("/AllQuestions/difficulty/{difficulty_level}")
    public ResponseEntity<List<Question>> getAllQuestionsByDifficultyLevel(@PathVariable String difficulty_level) {
       return qService.getAllQuestionsByDifficultyLevel(difficulty_level);
   }

    @Operation(summary = "Add multiple new questions")
   @PostMapping("/AllQuestions")
    public ResponseEntity<List<Question>> addMultipleQuestions(@RequestBody List<Question> ques) {
       return qService.addMultipleQuestions(ques);
   }
    @Operation(summary = "Update multiple new questions")
   @PutMapping("/AllQuestions")
    public ResponseEntity<List<Question>> updateQuestions(@RequestBody List<Question> ques) {
       return qService.updateQuestions(ques);
   }

}
