package com.example.shortie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "urls")

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShortieEntity {
    @Id
    @Column(unique = true)
    private String hashedUrl;

    @Column(nullable = false)
    private String longUrl;

    @Column(nullable = false)
    private String timeStamp;

}
