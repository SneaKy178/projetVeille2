package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.Entrevue;
import com.group1.stagesWs.service.EntrevueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/entrevue")
public class EntrevueController {

    private final EntrevueService entrevueService;


    @PostMapping("")
    public ResponseEntity<Entrevue> saveEntrevue(@RequestBody Entrevue entrevue) {
        return entrevueService.saveEntrevue(entrevue)
                .map(entrevue1 -> ResponseEntity.status(HttpStatus.CREATED).body(entrevue1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }


    public EntrevueController(EntrevueService entrevueService) {
        this.entrevueService = entrevueService;
    }

    @GetMapping(path = "/etudiant/{id}")
    public ResponseEntity<List<Entrevue>> getAllEntrevuesByEtudiant(@PathVariable("id") int id) {
        return new ResponseEntity<>(entrevueService.getAllEntrevueEtudiant(id), HttpStatus.OK);
    }

    @GetMapping(path = "/moniteur/{id}")
    public ResponseEntity<List<Entrevue>> getAllEntrevuesByMoniteur(@PathVariable("id") int id) {
        return new ResponseEntity<>(entrevueService.getAllEntrevueMoniteur(id), HttpStatus.OK);
    }
}
