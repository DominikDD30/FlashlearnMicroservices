package com.dominik.flashcardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharedSetDTO {
    private boolean isFlashcardsSet;
    private String owner;
    private FlashcardsSetDTO flashcardsSetDTO;
    private QuizDTO quizDTO;
}
