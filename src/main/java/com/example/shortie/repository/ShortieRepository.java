package com.example.shortie.repository;

import com.example.shortie.entity.ShortieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortieRepository extends JpaRepository<ShortieEntity,String> {
}
