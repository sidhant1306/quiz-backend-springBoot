package org.example.springboot_project_first.services;

import org.example.springboot_project_first.dto.ScoreResponse;
import org.example.springboot_project_first.exceptionHandling.ResourceNotFoundException;
import org.example.springboot_project_first.model.Question;
import org.example.springboot_project_first.model.Response;
import org.example.springboot_project_first.dto.QuestionWrapper;
import org.example.springboot_project_first.model.Quiz;
import org.example.springboot_project_first.repository.QuestionRepo;
import org.example.springboot_project_first.repository.QuizRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class QuizService {
    private QuizRepo quizRepo;
    private QuestionRepo queRepo;


    // constructor injection for questionRepo and quizRepo :
    public QuizService(QuizRepo repo, QuestionRepo qRepo) {
        this.quizRepo = repo;
        this.queRepo = qRepo;
    }

    // category is the category of which questions we want to create quiz with.
    // noOfQue are the number of questions we want to add in our quiz from questions(26) table.

    public ResponseEntity<String> createQuiz(String category, int noOfQue, String title) {
        try{
            // here this list of questions fetches the questions from the questions table on basis of our query.
            List<Question> questions = queRepo.findRandomQuestionsByCategory(category, noOfQue);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);

            // now our new quiz object is created, id is auto increment, we have got the title, we fetched random questions too.

            quizRepo.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.OK);

        }catch (Exception e) {
            e.printStackTrace();
        }
        // if things go wrong :
        return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for(Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    // function to calculate score :

    public ResponseEntity<ScoreResponse> calculateScore(Integer id, List<Response> res) {
        Quiz quiz = quizRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " +id));
        List<Question> questions = quiz.getQuestions();
        Map<Integer, Question> map = new HashMap<>();
        for (Question q : questions) {
            map.put(q.getId(), q);
        }

        int score = 0;
        for (Response r : res) {
            Question q = map.get(r.getQuestionId());
            if (q != null && r.getSubmittedAnswer().equals(q.getRightAnswer())) {
                score++;
            }
        }
        ScoreResponse scoreResponse = new ScoreResponse(score, questions.size());
        return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
    }
}
// it means iterate over the responses that we have submitted and get each submitted answer,
// and then if it is equal to the first index's (i)(first question), right answer then score++
// and after we are done with this, the next response will come automatically because of the enhanced loop,
// so we need to manually increase the value of i too, to get to the next question and find the right answer of that question.