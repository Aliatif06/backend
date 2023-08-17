package com.project.elearning.repository;

import com.project.elearning.models.Enrollement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollementRepository extends JpaRepository<Enrollement,Long> {
}
