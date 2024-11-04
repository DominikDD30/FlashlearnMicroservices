package com.dominik.pexelservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PexelPhoto {
    private int width;
    private int height;
    private Data src;


   public record Data(String small, String tiny){};

}


