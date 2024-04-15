package com.infy.hospitalmanagement.dto;

import com.infy.hospitalmanagement.entity.Patient;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Integer patientId;
    @NotNull(message="{patient.name.notPresent}")
    //@Pattern(regexp = "[A-Za-z]+(\\s[A-Za-z])*",message = "{patient.name.invalid}")
    @Pattern(regexp = "[\\p{L}0-9\\s\\-\\'\\.,]+", message = "{patient.name.invalid}")
    private String patientName;
    @NotNull(message = "{patient.gender.notPresent}")
    @Pattern(regexp = "(Male|Female|Others)")
    private String gender;
    private LocalDate dateOfBirth;
    @NotNull(message = "{patient.admissionDate.notPresent}")
    @PastOrPresent(message = "{patient.admissionDate.invalid}")
    @FutureOrPresent(message ="{patient.admissionDate.invalid}" )
    private LocalDate admissionDate;
    @NotNull(message = "{patient.diagnosis.notPresent}")
    @Pattern(regexp = "[A-za-z0-9]+",message = "{patient.diagnosis.invalid}")
    private  String diagnosis;

    public  static  Patient prepareEntity(PatientDTO patientDTO){
        // converting PatientDTo into Patient Jpa entity
        Patient patient = new Patient();
        patient.setPatientName(patientDTO.getPatientName());
        patient.setGender(patientDTO.getGender());
        patient.setAdmissionDate(patientDTO.getAdmissionDate());
        patient.setDiagnosis(patientDTO.getDiagnosis());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        return patient;
    }

    public  static PatientDTO prepareDTO(Patient patient){

        // converting Patient jpa entity into PatientDTO
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(patient.getPatientId());
        patientDTO.setPatientName(patient.getPatientName());
        patientDTO.setGender(patient.getGender());
        patientDTO.setDiagnosis(patient.getDiagnosis());
        patientDTO.setAdmissionDate(patient.getAdmissionDate());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        return  patientDTO;


    }
}
