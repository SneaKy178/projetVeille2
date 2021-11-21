package com.group1.stagesWs.model;

import com.group1.stagesWs.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Entrevue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private LocalDate date;
    private LocalTime time;
    private String nomEntreprise;

    private Status status;


    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Moniteur moniteur;

    public Entrevue() {
        this.status = Status.PENDING;


    }



}
