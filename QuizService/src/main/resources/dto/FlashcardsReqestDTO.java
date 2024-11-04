package com.dominik.flashcardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashcardsReqestDTO {
    private List<String> flashcards;
    private String language;
}
