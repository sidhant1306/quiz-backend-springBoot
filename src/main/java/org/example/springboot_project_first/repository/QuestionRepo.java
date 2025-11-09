package org.example.springboot_project_first.repository;

import org.example.springboot_project_first.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findAllByCategory(String category);

    List<Question> findAllBydifficultyLevel(String difficulty_level);

    // for creating a random quiz by taking questions from this question table :

    @Query(value = "SELECT * FROM Question q where q.category=:category ORDER BY RAND() LIMIT :nOfQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int nOfQ);
}