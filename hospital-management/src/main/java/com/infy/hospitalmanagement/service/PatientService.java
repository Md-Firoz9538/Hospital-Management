package com.infy.hospitalmanagement.service;

import com.infy.hospitalmanagement.dto.PatientDTO;
import com.infy.hospitalmanagement.exception.PatientAdmissionException;

import java.util.List;

public interface PatientService {

    List<PatientDTO> getListOfPatients(String diagnosis) throws PatientAdmissionException;

    public PatientDTO admitPatient(PatientDTO patientDTO) throws PatientAdmissionException;
}
