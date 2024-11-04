package com.dominik.deeplservice;

import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslateResponse {
    private String text;
}
