package com.dominik.quizservice;

import com.dominik.quizservice.controller.QuizController;
import com.dominik.quizservice.dto.QuizDTO;
import com.dominik.quizservice.fixtures.EntityFixtures;
import com.dominik.quizservice.fixtures.FixturesDTO;
import com.dominik.quizservice.service.QuizService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {
    @InjectMocks
    private QuizController quizController;

    @Mock
    private QuizService quizService;

    @Test
    void thatGetQuizForUserWorkCorrectly() {
        //given
        int userId=1;
        when(quizService.getSetsForUser(userId))
                .thenReturn(List.of(EntityFixtures.someQuizEntity1(), EntityFixtures.someQuizEntity2()));

        //when
        List<QuizDTO> result = quizController.getSetsForUser(userId);
        //then
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("questionDTOS")
                .containsExactlyInAnyOrder(
                        FixturesDTO.someQuizDto1(),
                        FixturesDTO.someQuizDto2());
    }

}