package com.example.shortie.service;

import com.example.shortie.dto.ShortieResponseDTO;
import com.example.shortie.entity.ShortieEntity;
import com.example.shortie.repository.ShortieRepository;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ShortieService {
    private final ShortieRepository shortieRepository;

    private String hashUrl(String longUrl) {
        long murmurHash = Hashing.murmur3_128().hashString(longUrl, StandardCharsets.UTF_8).asLong();
        return Long.toHexString(murmurHash);
    }

    public ShortieResponseDTO createShortUrl(String longUrl) {
        String hashedUrl = hashUrl(longUrl);
        // get the time stamp
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeStamp = LocalDateTime.now().format(dateTimeFormatter);

        //check if the hashed url already exists

        if (shortieRepository.existsById(hashedUrl)) {
            var shortieEntity = shortieRepository.findById(hashedUrl);
            return ShortieResponseDTO
                    .builder()
                    .shortUrl(shortieEntity.get().getHashedUrl())
                    .longUrl(shortieEntity.get().getLongUrl())
                    .timeStamp(shortieEntity.get().getTimeStamp())
                    .build();
        }
        // save url
        shortieRepository.save(ShortieEntity.builder().longUrl(longUrl).hashedUrl(hashedUrl).timeStamp(timeStamp).build());


        return ShortieResponseDTO.builder().shortUrl(hashedUrl).longUrl(longUrl).timeStamp(timeStamp).build();
    }

    public String resolveUrl(String shortUrl) {
        var shortieEntity = shortieRepository.findById(shortUrl);

        return shortieEntity.get().getLongUrl();

    }

}
