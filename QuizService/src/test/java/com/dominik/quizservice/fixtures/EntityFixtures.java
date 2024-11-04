package com.dominik.quizservice.fixtures;


import com.dominik.quizservice.entity.AnswerEntity;
import com.dominik.quizservice.entity.QuestionEntity;
import com.dominik.quizservice.entity.QuizEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

public class EntityFixtures {

    public static String someUUID1(){
        return "7b3c9cc4-99fa-4f8b-b759-2107f0a94893";
    }
    public  static QuizEntity someQuizEntity1(){
        return QuizEntity.builder()
                .quizId(1)
                .name("facts")
                .shareCode(someUUID1())
                .lastTimeUsed(LocalDate.of(2024, Month.APRIL,10))
                .questions(Set.of(someQuestionEntity1(), someQuestionEntity2()))
                .build();
    }

    public  static QuizEntity someQuizEntity2(){
        return QuizEntity.builder()
                .quizId(2)
                .name("facts2")
                .shareCode(someUUID1())
                .lastTimeUsed(LocalDate.of(2024, Month.APRIL,3))
                .questions(Set.of(someQuestionEntity2()))
                .build();
    }


    public static QuestionEntity someQuestionEntity1(){
        return QuestionEntity.builder()
                .questionId(1)
                .question("is sky blue")
                .answers(List.of(someCorrectAnswer1(),someWrongAnswer1()))
                .build();
    }

    public static QuestionEntity someQuestionEntity2(){
        return QuestionEntity.builder()
                .questionId(2)
                .question("is water green")
                .answers(List.of(someCorrectAnswer2(),someWrongAnswer2()))
                .build();
    }

    public static AnswerEntity someCorrectAnswer1(){
        return AnswerEntity.builder()
                .answerId(1)
                .value("yes")
                .isCorrect(true)
                .build();
    }

    public static AnswerEntity someCorrectAnswer2(){
        return AnswerEntity.builder()
                .answerId(2)
                .value("no")
                .isCorrect(true)
                .build();
    }

    public static AnswerEntity someWrongAnswer1(){
        return AnswerEntity.builder()
                .answerId(3)
                .value("no")
                .isCorrect(false)
                .build();
    }

    public static AnswerEntity someWrongAnswer2(){
        return AnswerEntity.builder()
                .answerId(4)
                .value("yes")
                .isCorrect(false)
                .build();
    }
}
