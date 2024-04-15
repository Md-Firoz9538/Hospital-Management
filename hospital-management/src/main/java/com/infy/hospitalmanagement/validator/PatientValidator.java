package com.infy.hospitalmanagement.validator;

import com.infy.hospitalmanagement.dto.PatientDTO;
import com.infy.hospitalmanagement.exception.PatientAdmissionException;

import java.time.LocalDate;

public class PatientValidator {
    public PatientValidator() {
        super();
    }
    public  static  void validatePatient(PatientDTO patientDTO) throws PatientAdmissionException{

      if(!isValidDateOfBirth(patientDTO.getDateOfBirth())){
          throw new PatientAdmissionException("patientValidator.INVALID_DOB");
      }

    }

    public static Boolean isValidDateOfBirth(LocalDate dateOfBirth) {
        LocalDate today=LocalDate.now();
        LocalDate future=today.minusYears(100);
        if(dateOfBirth.isAfter(today)|| dateOfBirth.isBefore(future)){
            return  false;
        }
        return true;
    }
}
