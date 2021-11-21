package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group1.stagesWs.enums.Status;

import java.util.List;

@Repository
public interface CVRepository extends JpaRepository<CV, Integer> {
    List<CV> findALlByEtudiantId(int id);

    CV findCvById(int id);

    List<CV> findAllByStatus(Status status);
}
