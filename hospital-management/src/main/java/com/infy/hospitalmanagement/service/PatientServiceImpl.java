package com.infy.hospitalmanagement.service;

import com.infy.hospitalmanagement.dto.PatientDTO;
import com.infy.hospitalmanagement.entity.Patient;
import com.infy.hospitalmanagement.exception.PatientAdmissionException;
import com.infy.hospitalmanagement.repository.PatientRepository;
import com.infy.hospitalmanagement.validator.PatientValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "patientService")
@Transactional
public class PatientServiceImpl  implements PatientService{
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public List<PatientDTO> getListOfPatients(String diagnosis) throws PatientAdmissionException {

        List<Patient> patients = patientRepository.findByDiagnosis(diagnosis);
        if (patients.isEmpty()){
            throw new PatientAdmissionException("PatientService.PATIENT_UNAVAILABLE");
        }
        List<PatientDTO> patientDTOs=new ArrayList<>();
        for (Patient patient:patients){
            PatientDTO patientDTO = PatientDTO.prepareDTO(patient);
            patientDTOs.add(patientDTO);
        }
        patientDTOs.sort((p1,p2)->p2.getAdmissionDate().compareTo(p1.getAdmissionDate()));

        return patientDTOs;
    }

    @Override
    public PatientDTO admitPatient(PatientDTO patientDTO) throws PatientAdmissionException {
        PatientValidator.validatePatient(patientDTO);

        Patient patient = PatientDTO.prepareEntity(patientDTO);
        Patient saved = patientRepository.save(patient);
        patientDTO.setPatientId(saved.getPatientId());

        return patientDTO;
    }
}
