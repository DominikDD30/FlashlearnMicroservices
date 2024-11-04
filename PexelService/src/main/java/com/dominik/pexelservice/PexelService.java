package com.dominik.pexelservice;

import com.dominik.pexelservice.domain.FlashcardDTO;
import com.dominik.pexelservice.domain.PexelPhotosResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PexelService {

    private PexelRestClient pexelRestClient;

    public List<FlashcardDTO> findImagesForFlashcards(List<String> flashcards, String language) {
        return flashcards.stream()
                .map(flashcard -> {
                    Optional<PexelPhotosResponse> photos = pexelRestClient.findImageForflashcard(flashcard, language);

                    return FlashcardDTO.builder()
                        .concept(flashcard)
                        .image(photos.isPresent()? photos.get().getPhotos().get(0).getSrc().tiny():null)
                        .build();
                }).toList();
    }
}
