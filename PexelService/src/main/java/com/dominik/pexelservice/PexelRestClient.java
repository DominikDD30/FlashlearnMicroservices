package com.dominik.pexelservice;

import com.dominik.pexelservice.domain.PexelPhotosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PexelRestClient {

    private final RestClient restClient;

    public Optional<PexelPhotosResponse> findImageForflashcard(String flashcard, String language) {
        try {
            return Optional.ofNullable(restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search")
                            .queryParam("per_page",1)
                            .queryParam("query",flashcard)
                            .queryParam("locale",language)
                            .build())
                    .retrieve()
                    .body(PexelPhotosResponse.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
