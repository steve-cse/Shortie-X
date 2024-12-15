package com.example.shortie.controller;

import com.example.shortie.dto.ShortieRequestDTO;
import com.example.shortie.dto.ShortieResponseDTO;
import com.example.shortie.service.ShortieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class ShortieController {
    private final ShortieService shortieService;
    @PostMapping("/")
    public ResponseEntity<ShortieResponseDTO> createShortUrl(@RequestBody ShortieRequestDTO shortieRequestDTO) throws NoSuchAlgorithmException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(shortieService.createShortUrl(shortieRequestDTO.getLongUrl()));

    }
    @GetMapping("/{shortUrl}")
    public ResponseEntity<HttpStatus> getShortUrl(@PathVariable String shortUrl){
        var targetUrl = shortieService.resolveUrl(shortUrl);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(targetUrl))
                .header(HttpHeaders.CONNECTION,"close")
                .build();
    }
}
