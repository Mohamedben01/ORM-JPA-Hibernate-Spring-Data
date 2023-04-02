package com.ben.springboot_app.repository;

import com.ben.springboot_app.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Optional<Medecin> findMedecinByNom(String name);
}
