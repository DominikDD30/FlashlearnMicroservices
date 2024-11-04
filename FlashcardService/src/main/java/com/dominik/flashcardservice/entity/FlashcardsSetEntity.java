package com.dominik.flashcardservice.entity;

import com.dominik.flashcardservice.entity.FlashcardEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"flashcardsSetId","name"})
@ToString(of = {"flashcardsSetId","name","lastTimeUsed","ownerId"})
@Entity
@Table(name = "flashcards_set")
public class FlashcardsSetEntity {

    @Id
    @Column(name = "flashcards_set_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flashcardsSetId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_time_used")
    private LocalDate lastTimeUsed;

    @Column(name = "share_code")
    private String shareCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flashcardsSet", cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private Set<FlashcardEntity> flashcards;

    @Column(name = "owner_id")
    private Integer ownerId;
}
