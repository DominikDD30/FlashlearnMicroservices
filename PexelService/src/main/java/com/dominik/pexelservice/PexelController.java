package com.dominik.pexelservice;

import com.dominik.pexelservice.domain.FlashcardDTO;
import com.dominik.pexelservice.domain.FlashcardsReqestDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(PexelController.PEXEL)
public class PexelController {

    public static final String PEXEL="/pexel";
    private PexelService pexelService;

    @PostMapping
    public List<FlashcardDTO>getPhotos(@RequestBody FlashcardsReqestDTO flashcards){
        return pexelService.findImagesForFlashcards(flashcards.getFlashcards(),flashcards.getLanguage());
    }
}
