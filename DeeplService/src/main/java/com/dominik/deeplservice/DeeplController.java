package com.dominik.deeplservice;

import com.deepl.api.DeepLException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(DeeplController.DEEPL)
public class DeeplController {
    public static final String DEEPL = "/deepl";

    private DeeplApiClient deeplApiClient;

    @GetMapping("/translate")
    public TranslateResponse translateText(String text, String source, String target){
        try {
            String translated = deeplApiClient.translate(text, source, target);
            return TranslateResponse.builder().text(translated).build();
        } catch (DeepLException | InterruptedException e) {
            return new TranslateResponse();
        }
    }
}
