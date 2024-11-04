package com.dominik.quizservice.controller;

import com.dominik.quizservice.dto.QuizDTO;
import com.dominik.quizservice.dto.SharedQuizDTO;
import com.dominik.quizservice.entity.QuizEntity;
import com.dominik.quizservice.mapper.QuizMapper;
import com.dominik.quizservice.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(QuizController.QUIZ)
public class QuizController {

    public static final String QUIZ="/quiz";
    private QuizService quizService;

    @PostMapping
    public void saveSet(@RequestBody QuizDTO quiz){
         quizService.saveSet(quiz);
    }

    @PutMapping("/{setId}")
    @Transactional
    public void updateSet(@PathVariable Integer setId,@RequestBody QuizDTO updatedSet){
        quizService.deleteSet(setId);
        quizService.saveSet(updatedSet);
    }

    @DeleteMapping("/{setId}")
    public void deleteSet(@PathVariable Integer setId){
        quizService.deleteSet(setId);
    }
    @GetMapping("/user/{userId}")
    public List<QuizDTO> getSetsForUser(@PathVariable Integer userId){
        return quizService.getSetsForUser(userId).stream()
                .map(quizSetEntity-> new QuizDTO(
                        quizSetEntity.getQuizId(),
                        0,
                        quizSetEntity.getName(),
                        quizSetEntity.getShareCode(),
                        quizSetEntity.getQuestions().stream().map(QuizMapper::mapFromEntity).toList(),
                        quizSetEntity.getQuestions().size()))
                .toList();
    }

    @PatchMapping("/{setId}/updateLastTimeUsed")
    public void updateLastTimeUsed(@PathVariable Integer setId){
        quizService.updateLastTimeUsed(setId);
    }

    @GetMapping("/shared/{shareCode}")
    public ResponseEntity<SharedQuizDTO> findSetByShareCode(@PathVariable String shareCode) {
        Optional<QuizEntity> quiz = quizService.getSetByShareCode(shareCode);

        if (quiz.isEmpty())return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        SharedQuizDTO sharedQuizDto = new SharedQuizDTO("John", quiz.get().getName());
        return new ResponseEntity<>(sharedQuizDto,HttpStatus.OK);
    }

}
