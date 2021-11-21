package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.enums.Status;
import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.CVRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import com.group1.stagesWs.repositories.WhitelistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private WhitelistRepository whitelistRepository;

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private UserService userService;

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public List<Offre> getEtudiantOffres(String etudiantEmail) {
        Etudiant etudiant = etudiantRepository.findEtudiantByCourrielIgnoreCase(etudiantEmail);
        List<Whitelist> whitelists = whitelistRepository.findAllByWhitelistedEtudiant(etudiant);
        return offreRepository.findAllByisValidTrueAndVisibiliteEtudiantIsNullOrVisibiliteEtudiantIn(whitelists);
    }

    public Optional<Offre> saveOffre(Offre offre) {
        if(offre.getVisibiliteEtudiant() != null) {
            whitelistRepository.save(offre.getVisibiliteEtudiant());
        }
        return Optional.of(offreRepository.save(offre));
    }

    public Optional<Whitelist> saveWhitelist(Whitelist whitelist) {
        return Optional.of(whitelistRepository.save(whitelist));
    }

    public Optional<CV> saveCV(CV cv) {
        return Optional.of(cvRepository.save(cv));
    }

    public List<CV> getAllCV(int id) {
        return cvRepository.findALlByEtudiantId(id);
    }

    public void deleteCV(int id) {
        cvRepository.deleteById(id);
    }

    public byte[] generateCVPDF(byte[] bArray, String fileName) {
        try {
            return bArray;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Optional<CV> acceptCV(CV cv) {
        cv.setStatus(Status.ACCEPTED);
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> rejectCV(CV cv) {
        cv.setStatus(Status.REJECTED);
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> getCV(int id) {
        return cvRepository.findById(id);
    }

    public List<CV> getAllCVs() {
        return cvRepository.findAll(Sort.by(Sort.Order.asc("status"), Sort.Order.desc("dateSoumission")));
    }
}
