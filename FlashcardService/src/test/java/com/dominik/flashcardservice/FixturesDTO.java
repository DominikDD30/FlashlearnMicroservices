package com.dominik.flashcardservice;


import com.dominik.flashcardservice.dto.FlashcardDTO;
import com.dominik.flashcardservice.dto.FlashcardSetGroupedByDate;
import com.dominik.flashcardservice.dto.FlashcardsSetDTO;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class FixturesDTO {


    public static String someUUID1(){
        return "7b3c9cc4-99fa-4f8b-b759-2107f0a94893";
    }
    public static List<FlashcardSetGroupedByDate>someFlashcardsSetGroupedByDate(){
        return List.of(
                new FlashcardSetGroupedByDate("today",List.of(someFlashcardsSetDto1())),
                new FlashcardSetGroupedByDate("thisWeek",List.of(someFlashcardsSetDto2())
                ));
    }
    public static FlashcardsSetDTO someFlashcardsSetDto1(){
        return FlashcardsSetDTO
                .builder()
                .setName("colors")
                .ownerId(1)
                .flashcards(List.of(someFlashcardDto1(),someFlashcardDto2(),someFlashcardDto3()))
                .shareCode(someUUID1())
                .flashcardsAmount(3)
                .lastTimeUsed(LocalDate.of(2024, Month.APRIL,10))
                .build();
    }

    public static FlashcardsSetDTO someFlashcardsSetDto2() {
        return FlashcardsSetDTO
                .builder()
                .setName("colors2")
                .ownerId(1)
                .flashcards(List.of(someFlashcardDto3(),someFlashcardDto2()))
                .shareCode(someUUID1())
                .flashcardsAmount(3)
                .lastTimeUsed(LocalDate.of(2024, Month.APRIL,2))
                .build();
    }



    public static FlashcardDTO someFlashcardDto1() {
        return FlashcardDTO.builder()
                .id(1)
                .concept("black")
                .definition("czarny")
                .image("some/image/url/13")
                .build();
    }

    public static FlashcardDTO someFlashcardDto2() {
        return FlashcardDTO.builder()
                .id(2)
                .concept("red")
                .definition("czerwony")
                .image("some/image/url/135643")
                .build();
    }

    public static FlashcardDTO someFlashcardDto3() {
        return FlashcardDTO.builder()
                .id(3)
                .concept("blue")
                .definition("niebieski")
                .image("some/image/url/1133")
                .build();
    }

}
