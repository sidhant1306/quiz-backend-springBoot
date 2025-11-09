package org.example.springboot_project_first.services;

import org.example.springboot_project_first.model.Question;
import org.example.springboot_project_first.repository.QuestionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    // we should return a ResponseEntity because responseEntity also returns a http response code along with the data.

    QuestionRepo repo;

    // constructor injection for question_repo class :
    public QuestionService(QuestionRepo repo) {
        this.repo = repo;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK); // first pass the data then pass the http response code
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); // if something goes wrong, return an empty arraylist and status.a
    }

    public  ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
         try{
             return new ResponseEntity<>(repo.findAllByCategory(category), HttpStatus.OK); // first pass the data then pass the http response code
         }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public Question getQuestionById(int id) {

        return repo.findById(id).orElse(new Question());
    }

    public  ResponseEntity<List<Question>> getAllQuestionsByDifficultyLevel(String difficulty_level) {
         try{
             return new ResponseEntity<>(repo.findAllBydifficultyLevel(difficulty_level), HttpStatus.OK);
         }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public  ResponseEntity<List<Question>> addMultipleQuestions(List<Question> questions) {
         try{
             return new ResponseEntity<>( repo.saveAll(questions), HttpStatus.CREATED);
         }catch (Exception e) {
            e.printStackTrace();
        }
         return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public  ResponseEntity<Question> addQuestion(Question que) {
         try{
             return new ResponseEntity<>(repo.save(que), HttpStatus.CREATED);
         }catch (Exception e) {
            e.printStackTrace();
        }
         return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    public  ResponseEntity<List<Question>> updateQuestions(List<Question> questions) {
         try{
             return new ResponseEntity<>(repo.saveAll(questions), HttpStatus.OK);
         }catch (Exception e) {
            e.printStackTrace();
        }
         return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
