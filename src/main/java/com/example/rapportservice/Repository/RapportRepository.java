package com.example.rapportservice.Repository;

import com.example.rapportservice.Entity.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
    Optional<Rapport> findByTitle(String title);
}
