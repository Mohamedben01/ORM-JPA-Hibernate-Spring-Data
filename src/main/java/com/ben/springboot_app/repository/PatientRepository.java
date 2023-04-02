package com.ben.springboot_app.repository;

import com.ben.springboot_app.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByNom(String name);
    @Query("SELECT p FROM Patient p WHERE p.nom like %:key%")
    List<Patient> findPatients(@Param("key") String keyword);
}
