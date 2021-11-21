package com.group1.stagesWs;

import com.group1.stagesWs.controller.EntrevueController;
import com.group1.stagesWs.enums.Status;
import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.*;
import com.group1.stagesWs.service.EntrevueService;
import com.group1.stagesWs.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@SpringBootApplication
public class StageswsApplication implements CommandLineRunner {

    @Autowired
    OffreRepository offreRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    MoniteurRepository moniteurRepository;

    @Autowired
    GestionnaireRepository gestionnaireRepository;

    @Autowired
    SuperviseurRepository superviseurRepository;

    @Autowired
    CVRepository cvRepository;

    @Autowired
    StageService service;

    @Autowired
    EntrevueRepository entrevueRepository;

    public static void main(String[] args) {
        SpringApplication.run(StageswsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



        Superviseur superviseur = new Superviseur();
        superviseur.setPrenom("Jeremie");
        superviseur.setNom("Munger");
        superviseur.setCourriel("jeremie@gmail.com");
        superviseur.setPassword("Password1");
        superviseur.setNumTelephone("82308920938");
        superviseur.setRole(UserType.SUPERVISEUR);
        superviseur.setDepartement("Informatique");
        superviseur.setSpecialite("fullstack");
        superviseurRepository.save(superviseur);

        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom("Mathieu");
        etudiant.setNom("Felton");
        etudiant.setCourriel("mat@gmail.com");
        etudiant.setPassword("Password1");
        etudiant.setNumTelephone("2323232323");
        etudiant.setRole(UserType.ETUDIANT);
        etudiant.setProgramme("Informatique");
        etudiant.setAdresse("113 lapierre");
        etudiant.setNumMatricule("1822323");
        etudiant.setHasLicense(true);
        etudiant.setSuperviseur(superviseur);
        etudiantRepository.save(etudiant);

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setPrenom("Patrick");
        etudiant1.setNom("Star");
        etudiant1.setCourriel("pat@gmail.com");
        etudiant1.setPassword("Password1");
        etudiant1.setNumTelephone("123145676");
        etudiant1.setRole(UserType.ETUDIANT);
        etudiant1.setProgramme("Info");
        etudiant1.setAdresse("113 lapierre");
        etudiant1.setNumMatricule("12345678");
        etudiant1.setHasLicense(true);
        etudiant1.setHasVoiture(true);
        etudiant1.setSuperviseur(superviseur);
        etudiantRepository.save(etudiant1);



        Moniteur moniteur = new Moniteur();
        moniteur.setPrenom("Pascal");
        moniteur.setNom("Bourgoin");
        moniteur.setCourriel("pascal@gmail.com");
        moniteur.setPassword("Password1");
        moniteur.setNumTelephone("2389238");
        moniteur.setRole(UserType.MONITEUR);
        moniteur.setNomEntreprise("Bob the builder");
        moniteur.setAdresseEntreprise("110 lapierre");
        moniteurRepository.save(moniteur);

        Gestionnaire gestionnaire = new Gestionnaire();
        gestionnaire.setPrenom("Neil");
        gestionnaire.setNom("Carrie");
        gestionnaire.setCourriel("neil@gmail.com");
        gestionnaire.setPassword("Password1");
        gestionnaire.setNumTelephone("879382378");
        gestionnaire.setRole(UserType.GESTIONNAIRE);
        gestionnaire.setDepartement("Informatique");
        gestionnaireRepository.save(gestionnaire);




        CV cv1 = new CV(); // pending
        cv1.setEtudiant(etudiant);
        cv1.setNom("cv-pending.pdf");
        CV cv2 = new CV(); // accepted
        cv2.setStatus(Status.ACCEPTED);
        cv2.setEtudiant(etudiant);
        cv2.setNom("cv-accepted.pdf");
        CV cv3 = new CV(); // rejected
        cv3.setStatus(Status.REJECTED);
        cv3.setEtudiant(etudiant);
        cv3.setNom("cv-rejected.pdf");
        cvRepository.saveAll(List.of(cv1, cv2, cv3));

        Offre offre1 = new Offre("TITRE1", "DESCRIPTION1", "ENTREPRISE1", true, "1 rue de la riviere Brossard", LocalDate.of(2021,12,05), LocalDate.of(2022,03,12), 13, "9:00 à 5:00", 40, 21);
        Offre offre2 = new Offre("TITRE2", "DESCRIPTION2", "ENTREPRISE2", true, "6 boul lachine Montreal", LocalDate.of(2021,12,05), LocalDate.of(2022,03,12), 13, "9:00 à 5:00", 40, 20);
        offreRepository.save(offre1);
        offreRepository.save(offre2);

        Entrevue entrevue = new Entrevue();
        entrevue.setId(1);
        entrevue.setTitre("test1");
        entrevue.setNomEntreprise("Umaknow");
        entrevue.setDate(LocalDate.of(2021,11,16));
        entrevue.setTime(LocalTime.of(15,00));
        entrevue.setEtudiant(etudiant);
        entrevue.setMoniteur(moniteur);
        entrevue.setStatus(Status.PENDING);

        Entrevue entrevue2 = new Entrevue();
        entrevue2.setId(2);
        entrevue2.setTitre("test2");
        entrevue2.setNomEntreprise("desJardins");
        entrevue2.setDate(LocalDate.of(2021,11,27));
        entrevue2.setTime(LocalTime.of(11,30));
        entrevue2.setEtudiant(etudiant);
        entrevue2.setMoniteur(moniteur);
        entrevue2.setStatus(Status.ACCEPTED);

        entrevueRepository.saveAll(List.of(entrevue,entrevue2));

    }
}
