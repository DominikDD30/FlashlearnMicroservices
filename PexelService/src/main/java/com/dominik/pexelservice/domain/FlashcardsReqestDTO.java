package com.dominik.pexelservice.domain;

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
