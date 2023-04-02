package com.ben.springboot_app.service;

import com.ben.springboot_app.entity.Patient;
import com.ben.springboot_app.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService{

    private final PatientRepository patientRepository;
    @Override
    public List<Patient> allPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> findPatients(String keyword) {
        return patientRepository.findPatients(keyword);
    }

    @Override
    public Optional<Patient> findPatientByName(String name) {
        return patientRepository.findPatientByNom(name);
    }

    @Override
    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public void addPatient(Patient patient) {
        Optional<Patient> existPatient = findPatientByName(patient.getNom());
        if(existPatient.isPresent())
            throw new RuntimeException("This patient already exist.");
        else patientRepository.save(patient);
    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        Optional<Patient> currentPatient = findPatientById(id);
        if(currentPatient.isPresent()){
            currentPatient.get().setNom(patient.getNom());
            currentPatient.get().setDateNaissance(patient.getDateNaissance());
            currentPatient.get().setScore(patient.getScore());
            currentPatient.get().setMalade(patient.isMalade());
            patientRepository.save(currentPatient.get());
        }
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
