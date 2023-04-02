package com.ben.springboot_app.controller;

import com.ben.springboot_app.entity.Patient;
import com.ben.springboot_app.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final IPatientService service;

    @GetMapping("/all")
    public ResponseEntity getAllPatient(){
        return ResponseEntity.ok().body(service.allPatients());
    }

    @GetMapping("/filter/{keyword}")
    public ResponseEntity filterPatients(@PathVariable("keyword") String keyword){
        return ResponseEntity.ok().body(service.findPatients(keyword));
    }
    @GetMapping("/byId/{id}")
    public ResponseEntity getPatientById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.findPatientById(id));
    }
    @GetMapping("/byName/{name}")
    public ResponseEntity getPatientById(@PathVariable("name") String name){
        return ResponseEntity.ok().body(service.findPatientByName(name));
    }
    @PostMapping("/insert")
    public ResponseEntity addPatient(@RequestBody Patient patient){
        service.addPatient(patient);
        return ResponseEntity.ok().body("Patient Saved Successfully.");
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient){
        service.updatePatient(id, patient);
        return ResponseEntity.ok().body("Patient Updated Successfully.");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePatient(@PathVariable("id") Long id){
        service.deletePatient(id);
        return ResponseEntity.ok().body("Patient deleted Successfully.");
    }
}
