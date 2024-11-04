package com.dominik.quizservice.repository;

import com.dominik.quizservice.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Integer> {

}
