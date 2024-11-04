package com.dominik.flashcardservice.mapper;


import com.dominik.flashcardservice.dto.FlashcardDTO;
import com.dominik.flashcardservice.entity.FlashcardEntity;

public interface FlashcardMapper {

    static FlashcardEntity mapToEntity(FlashcardDTO flashcardDTO){
       return FlashcardEntity.builder()
               .flashcardId(flashcardDTO.getId())
                .concept(flashcardDTO.getConcept())
                .definition(flashcardDTO.getDefinition())
                .imagePath(flashcardDTO.getImage())
                .build();
    }

    static FlashcardDTO mapFromEntity(FlashcardEntity flashcardEntity){
        return FlashcardDTO.builder()
                .id(flashcardEntity.getFlashcardId())
                .concept(flashcardEntity.getConcept())
                .definition(flashcardEntity.getDefinition())
                .image(flashcardEntity.getImagePath())
                .build();
    }




}
