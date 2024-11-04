package com.dominik.flashcardservice.controller;


import com.dominik.flashcardservice.dto.FlashcardSetGroupedByDate;
import com.dominik.flashcardservice.dto.FlashcardsSetDTO;
import com.dominik.flashcardservice.dto.SharedSetDTO;
import com.dominik.flashcardservice.entity.FlashcardsSetEntity;
import com.dominik.flashcardservice.mapper.FlashcardMapper;
import com.dominik.flashcardservice.mapper.FlashcardSetMapper;
import com.dominik.flashcardservice.service.FlashcardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(FlashcardController.FLASHCARDS)
public class FlashcardController {

    public static final String FLASHCARDS = "/flashcards";

    private final FlashcardService flashcardService;

//    private final Faker faker =new Faker();


    @PostMapping
    public void saveSet(@RequestBody FlashcardsSetDTO flashcards) {
        flashcardService.saveSetEntity(flashcards);
    }

    @GetMapping("/user/{userId}")
    public List<FlashcardSetGroupedByDate> getFlashcardSetsForUser(@PathVariable Integer userId) {
        return flashcardService.getSetsForUser(userId).entrySet().stream()
                .map(entrySet -> new FlashcardSetGroupedByDate(entrySet.getKey(), entrySet.getValue())).toList();
    }

    @PutMapping("/{setId}")
    @Transactional
    public void updateSet(@PathVariable Integer setId, @RequestBody FlashcardsSetDTO updatedSet) {
        FlashcardsSetEntity setEntity = flashcardService.getSetById(setId);
        setEntity.getFlashcards().forEach(flashcardService::deleteFlashcard);
        setEntity.setFlashcards(updatedSet.getFlashcards().stream()
                .map(FlashcardMapper::mapToEntity)
                .peek(flashcard -> flashcard.setFlashcardsSet(setEntity))
                .collect(Collectors.toSet()));
        setEntity.setName(updatedSet.getSetName());
        setEntity.setLastTimeUsed(LocalDate.now());

        flashcardService.saveSetEntity(setEntity);
    }

    @PatchMapping("/{setId}/updateLastTimeUsed")
    public void updateLastTimeUsed(@PathVariable Integer setId) {
        flashcardService.updateLastTimeUsed(setId);
    }

    @DeleteMapping("/{setId}")
    public void deleteSet(@PathVariable Integer setId) {
        flashcardService.deleteSet(setId);
    }

    @GetMapping("/{setId}")
    public FlashcardsSetDTO getFlashcardsSet(@PathVariable Integer setId) {
        FlashcardsSetEntity setEntity = flashcardService.getSetById(setId);
        return FlashcardSetMapper.mapFromEntity(setEntity);
    }

    @GetMapping("/shared/{shareCode}")
    public ResponseEntity<SharedSetDTO> findSetByShareCode(@PathVariable String shareCode) {
        Optional<FlashcardsSetEntity> set = flashcardService.getSetByShareCode(shareCode);

        if (set.isEmpty())return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        SharedSetDTO sharedQuizDto = new SharedSetDTO("John", set.get().getName());
        return new ResponseEntity<>(sharedQuizDto,HttpStatus.OK);
    }



}
