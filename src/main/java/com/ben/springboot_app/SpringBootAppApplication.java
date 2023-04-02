package com.ben.springboot_app;

import com.ben.springboot_app.entity.Consultation;
import com.ben.springboot_app.entity.Medecin;
import com.ben.springboot_app.entity.Patient;
import com.ben.springboot_app.entity.RendezVous;
import com.ben.springboot_app.entity.StatusRDV;
import com.ben.springboot_app.repository.ConsultationRepository;
import com.ben.springboot_app.repository.MedecinRepository;
import com.ben.springboot_app.repository.RendezVousRepository;
import com.ben.springboot_app.service.IPatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			IPatientService patientService,
			MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository,
			ConsultationRepository consultationRepository){
		return args -> {
			//Patient
			Stream.of("Ali", "Salma", "Fadi")
					.forEach(elm -> {
						Patient patient2 = Patient.builder()
								.nom(elm)
								.dateNaissance(new Date())
								.malade(false)
								.score(5)
								.build();
						patientService.addPatient(patient2);
					});

			Patient patient1 = patientService.findPatientById(2L).orElse(null);
			System.out.println("===>Patient By Id: "+patient1);
			Patient patient2 = patientService.findPatientByName("Salma").orElse(null);
			System.out.println("===>Patient By Name: "+patient2);
			List<Patient> patients = patientService.allPatients();
			patients.forEach(patient -> patient.toString());


			//Medecin
			Stream.of("Kaoutar", "Samira", "Hamid")
					.forEach( elm -> {
						Medecin medecin = Medecin.builder()
								.nom(elm)
								.email(elm+"@gmail.com")
								.specialite(Math.random() > 0.5 ? "Dentiste" : "Cardio")
								.build();
						medecinRepository.save(medecin);
					});
			Medecin medecin1 = medecinRepository.findMedecinByNom("Samira").orElse(null);
			Medecin medecin2 = medecinRepository.findMedecinByNom("Kaoutar").orElse(null);


			//RendezVous
			RendezVous rendezVous = RendezVous.builder()
					.date(new Date())
					.status(StatusRDV.PENDING)
					.medecin(medecin1)
					.patient(patient1)
					.build();
			rendezVousRepository.save(rendezVous);
			RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);

			//Consultation
			Consultation consultation = Consultation.builder()
					.dateConsultation(new Date())
					.rendezVous(rendezVous1)
					.rapport("Rapport de la consultation.")
					.build();
			consultationRepository.save(consultation);

		};
	}

}
