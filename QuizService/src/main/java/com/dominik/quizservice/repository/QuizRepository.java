package com.dominik.quizservice.repository;

import com.dominik.quizservice.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity,Integer> {

    Optional<List<QuizEntity>> getAllByOwnerId(Integer userId);

    Optional<QuizEntity> findByShareCode(String shareCode);
}
