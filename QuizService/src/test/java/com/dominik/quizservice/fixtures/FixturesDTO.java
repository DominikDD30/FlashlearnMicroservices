package com.dominik.quizservice.fixtures;


import com.dominik.quizservice.dto.AnswerDTO;
import com.dominik.quizservice.dto.QuestionDTO;
import com.dominik.quizservice.dto.QuizDTO;

import java.util.List;

public class FixturesDTO {


    public static String someUUID1() {
        return "7b3c9cc4-99fa-4f8b-b759-2107f0a94893";
    }


    public static QuizDTO someQuizDto1() {
        return QuizDTO.builder()
                .quizId(1)
                .shareCode(someUUID1())
                .setName("facts")
                .questionDTOS(List.of(someQuestionDto1(), someQuestionDto2()))
                .questionsAmount(2)
                .build();
    }

    public static QuizDTO someQuizDto2() {
        return QuizDTO.builder()
                .quizId(2)
                .setName("facts2")
                .shareCode(someUUID1())
                .questionDTOS(List.of(someQuestionDto2()))
                .questionsAmount(1)
                .build();
    }

    public static QuestionDTO someQuestionDto1() {
        return new QuestionDTO(
                1,
                "is sky blue",
                List.of(someCorrectAnswer1(), someWrongAnswer1())
        );
    }

    public static QuestionDTO someQuestionDto2() {
        return new QuestionDTO(
                2,
                "is water green",
                List.of(someCorrectAnswer2(), someWrongAnswer2())
        );
    }

    public static AnswerDTO someCorrectAnswer1() {
        return new AnswerDTO(
                1,
                "yes",
                true
        );
    }


    public static AnswerDTO someCorrectAnswer2() {
        return new AnswerDTO(
                2,
                "no",
                true
        );
    }

    public static AnswerDTO someWrongAnswer1() {
        return new AnswerDTO(
                3,
                "no",
                false
        );
    }

    public static AnswerDTO someWrongAnswer2() {
        return new AnswerDTO(
                4,
                "yes",
                false
        );
    }


}
