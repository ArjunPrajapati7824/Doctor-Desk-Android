package com.example.doctordesk.doctor.Model;

public class DoctorModel_Doctor {

    String Dr_Name, Clinic_Name, Clinic_Address, Specialization,Doctor_Id,Dr_PhoneNumber,Dr_Registration_number;


    public DoctorModel_Doctor(String dr_Name, String clinic_Name, String clinic_Address, String specialization, String doctor_Id, String dr_PhoneNumber, String dr_Registration_number) {
        Dr_Name = dr_Name;
        Clinic_Name = clinic_Name;
        Clinic_Address = clinic_Address;
        Specialization = specialization;
        Doctor_Id = doctor_Id;
        Dr_PhoneNumber = dr_PhoneNumber;
        Dr_Registration_number = dr_Registration_number;
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

    public String getDr_PhoneNumber() {
        return Dr_PhoneNumber;
    }

    public void setDr_PhoneNumber(String dr_PhoneNumber) {
        Dr_PhoneNumber = dr_PhoneNumber;
    }

    public String getDr_Registration_number() {
        return Dr_Registration_number;
    }

    public void setDr_Registration_number(String dr_Registration_number) {
        Dr_Registration_number = dr_Registration_number;
    }
}

