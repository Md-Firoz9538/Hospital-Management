package com.infy.hospitalmanagement.repository;

import com.infy.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findByDiagnosis(String diagnosis);

}

