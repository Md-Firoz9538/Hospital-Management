package com.infy.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    private String patientName;
    private String gender;
    private LocalDate dateOfBirth;
    private LocalDate admissionDate;
    private  String diagnosis;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(patientId, patient.patientId) && Objects.equals(patientName, patient.patientName) && Objects.equals(gender, patient.gender) && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(admissionDate, patient.admissionDate) && Objects.equals(diagnosis, patient.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, patientName, gender, dateOfBirth, admissionDate, diagnosis);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", admissionDate=" + admissionDate +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }



}
