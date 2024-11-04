package com.dominik.quizservice.mapper;


import com.dominik.quizservice.dto.AnswerDTO;
import com.dominik.quizservice.dto.QuestionDTO;
import com.dominik.quizservice.entity.AnswerEntity;
import com.dominik.quizservice.entity.QuestionEntity;

import java.util.stream.Collectors;


public interface QuizMapper {

    static QuestionEntity mapToEntity(QuestionDTO questionDTO){
       return QuestionEntity.builder()
               .question(questionDTO.getQuestion())
               .answers(questionDTO.getAnswers().stream().map(QuizMapper::mapToEntity).collect(Collectors.toList()))
                .build();
    }

    static QuestionDTO mapFromEntity(QuestionEntity questionEntity) {
        return new QuestionDTO(questionEntity.getQuestionId(), questionEntity.getQuestion(), questionEntity.getAnswers().stream().map(QuizMapper::mapFromEntity).toList());
    }

    static AnswerDTO mapFromEntity(AnswerEntity answerEntity){
        return new AnswerDTO(answerEntity.getAnswerId(), answerEntity.getValue(),answerEntity.getIsCorrect());
    }
    static AnswerEntity mapToEntity(AnswerDTO answerDTO){
        return AnswerEntity.builder()
                .value(answerDTO.getValue())
                .isCorrect(answerDTO.getIsCorrect())
                .build();
    }



}
