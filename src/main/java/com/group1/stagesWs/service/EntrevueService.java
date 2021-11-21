package com.group1.stagesWs.service;



import com.group1.stagesWs.model.Entrevue;
import com.group1.stagesWs.repositories.EntrevueRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EntrevueService {

    private final EntrevueRepository entrevueRepository;

    public EntrevueService(EntrevueRepository entrevueRepository) {
        this.entrevueRepository = entrevueRepository;

    }

    public Optional<Entrevue> saveEntrevue(Entrevue entrevue) {
        return Optional.of(entrevueRepository.save(entrevue));
    }

    public List<Entrevue> getAllEntrevueEtudiant(int id) {
        List<Entrevue> listEtudiantCurrentSession = entrevueRepository.findEntrevueByEtudiantId(id);
        return listEtudiantCurrentSession;
    }

    public List<Entrevue> getAllEntrevueMoniteur(int id) {
        List<Entrevue> listMoniteurCurrentSession = entrevueRepository.findEntrevueByMoniteurId(id);
        return listMoniteurCurrentSession;
    }
}





