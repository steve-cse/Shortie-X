package com.example.shortie.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortieResponseDTO {
    private String longUrl;
    private String shortUrl;
    private String timeStamp;
}
