package com.group1.stagesWs.model;


import com.group1.stagesWs.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class CV implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private LocalDate dateSoumission;
    private Status status;

    @Lob
    private byte[] data;

    @ManyToOne
    private Etudiant etudiant;

    public CV() {
        this.dateSoumission = LocalDate.now();
        status = Status.PENDING;
    }
}
