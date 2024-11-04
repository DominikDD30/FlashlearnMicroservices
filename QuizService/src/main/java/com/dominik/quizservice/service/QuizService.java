package com.dominik.quizservice.service;

import com.dominik.quizservice.dto.QuizDTO;
import com.dominik.quizservice.entity.QuestionEntity;
import com.dominik.quizservice.entity.QuizEntity;
import com.dominik.quizservice.mapper.QuizMapper;
import com.dominik.quizservice.repository.AnswerRepository;
import com.dominik.quizservice.repository.QuestionRepository;
import com.dominik.quizservice.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizService {

    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;


    @Transactional
    public void saveSet(QuizDTO quizDTO) {
        QuizEntity quizSet = QuizEntity.builder()
                .name(quizDTO.getSetName())
                .lastTimeUsed(LocalDate.now())
                .shareCode(UUID.randomUUID().toString())
                .questions(quizDTO.getQuestionDTOS().stream().map(QuizMapper::mapToEntity).collect(Collectors.toSet()))
                .ownerId(quizDTO.getOwnerId())
                .build();

        quizRepository.save(quizSet);
        questionRepository.saveAll(quizSet.getQuestions().stream()
                .peek(quizEntity -> quizEntity.setQuiz(quizSet)).collect(Collectors.toSet()));


        for (QuestionEntity questionEntity :quizSet.getQuestions()){
            questionEntity.setAnswers(questionEntity.getAnswers().stream()
                    .peek(answer -> answer.setQuestion(questionEntity)).collect(Collectors.toList()));
        }

        answerRepository.saveAll(quizSet.getQuestions().stream()
                .flatMap(quizEntity -> quizEntity.getAnswers().stream()).toList());

    }

    public List<QuizEntity> getSetsForUser(Integer userId) {
        return quizRepository.getAllByOwnerId(userId).orElse(List.of());
    }



    public void deleteSet(Integer setId) {
        quizRepository.deleteById(setId);
    }


    public Optional<QuizEntity> getSetByShareCode(String shareCode) {
      return   quizRepository.findByShareCode(shareCode);
    }

    public void updateLastTimeUsed(Integer setId) {
        QuizEntity setEntity = quizRepository.findById(setId).get();
        setEntity.setLastTimeUsed(LocalDate.now());
        quizRepository.save(setEntity);
    }
}
