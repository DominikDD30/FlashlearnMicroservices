package com.dominik.flashcardservice.service;

import com.dominik.flashcardservice.dto.FlashcardsSetDTO;
import com.dominik.flashcardservice.entity.FlashcardEntity;
import com.dominik.flashcardservice.entity.FlashcardsSetEntity;
import com.dominik.flashcardservice.mapper.FlashcardMapper;
import com.dominik.flashcardservice.repository.FlashcardRepository;
import com.dominik.flashcardservice.repository.FlashcardsSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlashcardService {

    private FlashcardsSetRepository flashcardsSetRepository;
    private FlashcardRepository flashcardRepository;
//    private ElevenLabApiClientImpl elevenLabApiClient;


    public void saveSetEntity(FlashcardsSetEntity flashcardsSetEntity) {
       // flashcardsSetEntity.getFlashcards().forEach(flashcard->saveSoundIfNotExist(flashcard.getConcept()));
        flashcardsSetRepository.save(flashcardsSetEntity);
    }
    @Transactional
    public void saveSetEntity(FlashcardsSetDTO flashcardsSetDTO) {
        FlashcardsSetEntity flashcardsSet = FlashcardsSetEntity.builder()
                .name(flashcardsSetDTO.getSetName())
                .lastTimeUsed(LocalDate.now())
                .shareCode(UUID.randomUUID().toString())
                .ownerId(flashcardsSetDTO.getOwnerId())
                .build();
        saveSetEntity(flashcardsSet);
        flashcardRepository.saveAll(flashcardsSetDTO.getFlashcards().stream()
               // .peek(flashcardDTO -> saveSoundIfNotExist(flashcardDTO.getConcept()))
                .map(flashcardDTO -> FlashcardMapper.mapToEntity(flashcardDTO).withFlashcardId(null))
                .peek(flashcard -> flashcard.setFlashcardsSet(flashcardsSet))
                .collect(Collectors.toSet()));
    }



//    private void saveSoundIfNotExist(String flashcardConcept) {
//        String filename = flashcardConcept + ".mp3";
//
//        try {
//            Path mpegFolder = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("mpeg_files/")).toURI());
//            Path filePath = mpegFolder.resolve(filename);
//
//            if (!Files.exists(filePath)) {
//                byte[] mpeg = elevenLabApiClient.saveMPEGForFlashcard(flashcardConcept);
//                try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
//                    fos.write(mpeg);
//                }
//            }
//        } catch (IOException | URISyntaxException e) {
//            throw new RuntimeException("Error while saving mpeg file", e);
//        }
//    }

    public Map<String, List<FlashcardsSetDTO>> getSetsForUser(Integer userId) {
        List<FlashcardsSetDTO> flashcardsSets = flashcardsSetRepository.getAllByOwner_UserId(userId).orElse(List.of())
                .stream().map(flashcardsSetEntity -> FlashcardsSetDTO.builder()
                        .id(flashcardsSetEntity.getFlashcardsSetId())
                        .setName(flashcardsSetEntity.getName())
                        .shareCode(flashcardsSetEntity.getShareCode())
                        .lastTimeUsed(flashcardsSetEntity.getLastTimeUsed())
                        .flashcardsAmount(flashcardsSetEntity.getFlashcards().size())
                        .build())
                .toList();
        return groupByDateCategory(flashcardsSets);
    }

    private Map<String, List<FlashcardsSetDTO>> groupByDateCategory(List<FlashcardsSetDTO> flashcardsSets) {
        Map<String, List<FlashcardsSetDTO>> groupedSets = new LinkedHashMap<>();

        // today
        LocalDate today = LocalDate.now();
        List<FlashcardsSetDTO> todaySets = flashcardsSets.stream()
                .filter(set -> set.getLastTimeUsed().isEqual(today)).toList();
        groupedSets.put("today", todaySets);

        // this week
        LocalDate thisWeekStart = LocalDate.now().minusWeeks(1);
        List<FlashcardsSetDTO> thisWeekSets = flashcardsSets.stream()
                .filter(set -> set.getLastTimeUsed().isAfter(thisWeekStart.minusDays(1))&&set.getLastTimeUsed().isBefore(today)).toList();
        groupedSets.put("thisWeek", thisWeekSets);

        // previous week
        LocalDate prevWeekStart = thisWeekStart.minusWeeks(1);
        List<FlashcardsSetDTO> prevWeekSets = flashcardsSets.stream()
                .filter(set -> set.getLastTimeUsed().isAfter(prevWeekStart.minusDays(1))&&set.getLastTimeUsed().isBefore(thisWeekStart)).toList();
        groupedSets.put("pastWeek", prevWeekSets);

        Map<String, List<FlashcardsSetDTO>> rest = flashcardsSets.stream()
                .filter(set ->set.getLastTimeUsed().isBefore(prevWeekStart.minusDays(1)))
                .sorted(Comparator.comparing(FlashcardsSetDTO::getLastTimeUsed).reversed())
                .collect(Collectors.groupingBy(
                        set -> set.getLastTimeUsed().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));;

        groupedSets.putAll(rest);

        return groupedSets;
    }

    public FlashcardsSetEntity getSetById(Integer setId) {
        return flashcardsSetRepository.findById(setId).get();
    }

    public void deleteSet(Integer setId) {
        flashcardsSetRepository.deleteById(setId);
    }


    public void deleteFlashcard(FlashcardEntity flashcardEntity) {
        flashcardRepository.delete(flashcardEntity);
    }

    public void updateLastTimeUsed(Integer setId) {
        FlashcardsSetEntity setEntity = flashcardsSetRepository.findById(setId).get();
        setEntity.setLastTimeUsed(LocalDate.now());
        flashcardsSetRepository.save(setEntity);
    }
}
