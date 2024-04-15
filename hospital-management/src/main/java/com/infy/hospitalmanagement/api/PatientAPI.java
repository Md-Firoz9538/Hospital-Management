package com.infy.hospitalmanagement.api;


import com.infy.hospitalmanagement.dto.PatientDTO;
import com.infy.hospitalmanagement.exception.PatientAdmissionException;
import com.infy.hospitalmanagement.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api")
@Validated
public class PatientAPI {
    @Autowired
    private PatientService patientService;

    //for getting details by diagnosis

    @GetMapping(value = "patients/{diagnosis}")
    public ResponseEntity<List<PatientDTO>> getPatientByDiagnosis(@PathVariable
     @Pattern(regexp = "[A-za-z0-9]+(\\s[A-Za-z0-9]+)*",message = "{patient.diagnosis.invalid}")   String diagnosis) throws PatientAdmissionException {

        List<PatientDTO> patientDTOs = patientService.getListOfPatients(diagnosis);
        return new ResponseEntity<>(patientDTOs, HttpStatus.OK);

    }
    @PostMapping(value = "patients",consumes = "application/json")
    public ResponseEntity<PatientDTO> admitPatient( @Valid @RequestBody PatientDTO patientDTO) throws PatientAdmissionException {
        PatientDTO admitPatient = patientService.admitPatient(patientDTO);
        return new ResponseEntity<>(admitPatient,HttpStatus.CREATED);

    }

}
