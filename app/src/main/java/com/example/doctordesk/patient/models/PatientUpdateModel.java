package com.example.doctordesk.patient.models;

public class PatientUpdateModel {
            String Patient_Name="ajun",Patient_PhoneNumber,Patient_Age,Patient_City,Patient_Weight,Patient_Id;


    public PatientUpdateModel() {
    }

    public PatientUpdateModel(String patient_Name, String patient_PhoneNumber, String patient_Age, String patient_City, String patient_Weight, String patient_Id) {
        Patient_Name = patient_Name;
        Patient_PhoneNumber = patient_PhoneNumber;
        Patient_Age = patient_Age;
        Patient_City = patient_City;
        Patient_Weight = patient_Weight;
        Patient_Id=patient_Id;
    }

    public String getPatient_Name() {
        return Patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        Patient_Name = patient_Name;
    }

    public String getPatient_PhoneNumber() {
        return Patient_PhoneNumber;
    }

    public void setPatient_PhoneNumber(String patient_PhoneNumber) {
        Patient_PhoneNumber = patient_PhoneNumber;
    }

    public String getPatient_Age() {
        return Patient_Age;
    }

    public void setPatient_Age(String patient_Age) {
        Patient_Age = patient_Age;
    }

    public String getPatient_City() {
        return Patient_City;
    }

    public void setPatient_City(String patient_City) {
        Patient_City = patient_City;
    }

    public String getPatient_Weight() {
        return Patient_Weight;
    }

    public void setPatient_Weight(String patient_Weight) {
        Patient_Weight = patient_Weight;
    }

    public String getPatient_Id() {
        return Patient_Id;
    }

    public void setPatient_Id(String patient_Id) {
        Patient_Id = patient_Id;
    }
}
