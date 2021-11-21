package com.group1.stagesWs.controller;


import com.group1.stagesWs.model.*;
import com.group1.stagesWs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @PostMapping(path = "/stage/etudiant")
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
        logger.info("post - createEtudiant " + etudiant);
        return service.addEtudiant(etudiant)
                .map(etudiant1 -> ResponseEntity.status(HttpStatus.CREATED).body(etudiant1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    //login
    @GetMapping("/user/{email}/{password}")
    public ResponseEntity<User> login(@PathVariable("email") String email, @PathVariable("password") String password) {
        return service.login(email, password)
                .map(etudiant1 -> ResponseEntity.status(HttpStatus.OK).body(etudiant1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable("email") String email) {
        return service.findUserByCourriel(email)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }


    //Create Moniteur
    @PostMapping(path = "/stage/moniteur")
    public ResponseEntity<Moniteur> addMoniteur(@RequestBody Moniteur moniteur) {
        return service.addMoniteur(moniteur)
                .map(moniteur1 -> ResponseEntity.status(HttpStatus.CREATED).body(moniteur1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("/stage/superviseur")
    public ResponseEntity<Superviseur> addSuperviseur(@RequestBody Superviseur superviseur) {
        return service.addSuperviseur(superviseur)
                .map(superviseur1 -> ResponseEntity.status(HttpStatus.CREATED).body(superviseur1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping(path = "/stage/etudiants")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        return new ResponseEntity<>(service.getAllEtudiants(), HttpStatus.OK);
    }

    @GetMapping(path = "/stage/moniteurs")
    public ResponseEntity<List<Moniteur>> getAllMoniteurs() {
        return new ResponseEntity<>(service.getAllMoniteurs(), HttpStatus.OK);
    }

    @GetMapping(path = "/stage/superviseurs")
    public ResponseEntity<List<Superviseur>> getAllSuperviseurs() {
        return new ResponseEntity<>(service.getAllSuperviseurs(), HttpStatus.OK);
    }

    @GetMapping(path = "/stage/superviseur/etudiants/{idSuperviseur}")
    public ResponseEntity<List<Etudiant>> getAllEtudiantBySuperviseur(@PathVariable int idSuperviseur) {
        return new ResponseEntity<>(service.getAllEtudiantsBySuperviseur(idSuperviseur), HttpStatus.OK);
    }

    @GetMapping(path = "/stage/gestionnaire/{id}")
    public Gestionnaire getGestionnaire(@PathVariable int id) {
        return service.getGestionnaire(id);
    }

    @GetMapping(path = "stage/etudiant/{id}")
    public ResponseEntity<Etudiant> getEtudiant(@PathVariable("id") int id){
        return new ResponseEntity<>(service.getEtudiant(id), HttpStatus.OK);
    }
}
