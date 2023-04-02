package com.ben.springboot_app.service;

import com.ben.springboot_app.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    List<Patient> allPatients();
    List<Patient> findPatients(String keyword);
    Optional<Patient> findPatientByName(String name);
    Optional<Patient> findPatientById(Long id);
    void addPatient(Patient patient);
    void updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
}
