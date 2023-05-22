package com.example.doctordesk.patient.models;

public class DoctorModel {

    String Dr_Name, Clinic_Name, Clinic_Address, Specialization,Doctor_Id;


    public DoctorModel(String dr_Name, String clinic_Name, String clinic_Address, String specialization, String Doctor_Id) {
        Dr_Name = dr_Name;
        Clinic_Name = clinic_Name;
        Clinic_Address = clinic_Address;
        Specialization = specialization;
        this.Doctor_Id = Doctor_Id;
    }

    public DoctorModel() {
    }

    public String getDr_Name() {
        return Dr_Name;
    }

    public void setDr_Name(String dr_Name) {
        Dr_Name = dr_Name;
    }

    public String getClinic_Name() {
        return Clinic_Name;
    }

    public void setClinic_Name(String clinic_Name) {
        Clinic_Name = clinic_Name;
    }

    public String getClinic_Address() {
        return Clinic_Address;
    }

    public void setClinic_Address(String clinic_Address) {
        Clinic_Address = clinic_Address;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getDoctor_Id() {
        return Doctor_Id;
    }

    public void setDoctor_Id(String doctor_Id) {
        Doctor_Id = doctor_Id;
    }
}
