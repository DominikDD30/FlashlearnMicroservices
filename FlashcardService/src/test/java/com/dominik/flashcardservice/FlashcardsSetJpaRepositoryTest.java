package com.dominik.flashcardservice;

import com.dominik.flashcardservice.entity.FlashcardEntity;
import com.dominik.flashcardservice.entity.FlashcardsSetEntity;
import com.dominik.flashcardservice.repository.FlashcardRepository;
import com.dominik.flashcardservice.repository.FlashcardsSetRepository;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static com.dominik.flashcardservice.EntityFixtures.*;


@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FlashcardsSetJpaRepositoryTest {

    private FlashcardRepository flashcardRepository;
    private FlashcardsSetRepository flashcardsSetRepository;

    @Test
    void shouldSaveAndGetAllFlashcards() {
        //given
        flashcardRepository.deleteAll();
        Integer someUserId = 1;
        FlashcardsSetEntity setEntity = new FlashcardsSetEntity(null, "animals",
                LocalDate.now(), "asgsags", null, someUserId);
        flashcardsSetRepository.save(setEntity);
        flashcardRepository.saveAll(List.of(
                someFlashcardEntity1().withFlashcardId(null).withFlashcardsSet(setEntity),
                someFlashcardEntity2().withFlashcardId(null).withFlashcardsSet(setEntity),
                someFlashcardEntity3().withFlashcardId(null).withFlashcardsSet(setEntity)));
        //when
        List<FlashcardEntity> result = flashcardRepository.findAll();
        //then
        Assertions.assertThat(result).hasSize(3);
        Assertions.assertThat(result)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("flashcardId","flashcardsSet")
                .containsExactlyInAnyOrder(someFlashcardEntity1(),someFlashcardEntity2(),someFlashcardEntity3());
    }


}