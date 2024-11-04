package com.dominik.flashcardservice.mapper;


import com.dominik.flashcardservice.dto.FlashcardsSetDTO;
import com.dominik.flashcardservice.entity.FlashcardsSetEntity;

public interface FlashcardSetMapper {

    static FlashcardsSetDTO mapFromEntity(FlashcardsSetEntity flashcardsSet){
        return FlashcardsSetDTO.builder()
                .setName(flashcardsSet.getName())
                .flashcards(flashcardsSet.getFlashcards().stream().map(FlashcardMapper::mapFromEntity).toList())
                .build();
    }
}
