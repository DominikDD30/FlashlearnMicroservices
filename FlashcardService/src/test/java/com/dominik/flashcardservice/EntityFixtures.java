package com.dominik.flashcardservice;


import com.dominik.flashcardservice.entity.FlashcardEntity;

public class EntityFixtures {

    public static String someUUID1(){
        return "7b3c9cc4-99fa-4f8b-b759-2107f0a94893";
    }


    public static FlashcardEntity someFlashcardEntity1(){
        return FlashcardEntity.builder()
                .flashcardId(1)
                .concept("cat")
                .definition("kot")
                .imagePath("some/image/path12")
                .build();
    }

    public static FlashcardEntity someFlashcardEntity2(){
        return FlashcardEntity.builder()
                .flashcardId(2)
                .concept("dog")
                .definition("pies")
                .imagePath("some/image/path123")
                .build();
    }
    public static FlashcardEntity someFlashcardEntity3(){
        return FlashcardEntity.builder()
                .flashcardId(3)
                .concept("snake")
                .definition("wąż")
                .imagePath("some/image/path1234")
                .build();
    }
    public static FlashcardEntity someFlashcardEntity4(){
        return FlashcardEntity.builder()
                .flashcardId(4)
                .concept("whale")
                .definition("wieloryb")
                .imagePath("some/image/path1245")
                .build();
    }
    public static FlashcardEntity someFlashcardEntity5(){
        return FlashcardEntity.builder()
                .flashcardId(5)
                .concept("horse")
                .definition("koń")
                .imagePath(null)
                .build();
    }
    public static FlashcardEntity someFlashcardEntity6(){
        return FlashcardEntity.builder()
                .flashcardId(6)
                .concept("monkey")
                .definition("małpa")
                .imagePath(null)
                .build();
    }


}
