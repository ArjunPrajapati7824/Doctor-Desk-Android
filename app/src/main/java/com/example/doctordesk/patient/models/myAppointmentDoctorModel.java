package com.example.doctordesk.patient.models;

public class myAppointmentDoctorModel {

    String Dr_Name, Clinic_Name, Clinic_Address, Specialization,Doctor_Id,Appointment_Id,Appointment_Status,Patient_Id;

    public myAppointmentDoctorModel() {
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

    public String getAppointment_Id() {
        return Appointment_Id;
    }

    public void setAppointment_Id(String appointment_Id) {
        Appointment_Id = appointment_Id;
    }

    public String getAppointment_Status() {
        return Appointment_Status;
    }

    public void setAppointment_Status(String appointment_Status) {
        Appointment_Status = appointment_Status;
    }

    public String getPatient_Id() {
        return Patient_Id;
    }

    public void setPatient_Id(String patient_Id) {
        Patient_Id = patient_Id;
    }

    public myAppointmentDoctorModel(String dr_Name, String clinic_Name, String clinic_Address, String specialization, String doctor_Id, String appointment_Id, String appointment_Status, String patient_Id) {
        Dr_Name = dr_Name;
        Clinic_Name = clinic_Name;
        Clinic_Address = clinic_Address;
        Specialization = specialization;
        Doctor_Id = doctor_Id;
        Appointment_Id = appointment_Id;
        Appointment_Status = appointment_Status;
        Patient_Id = patient_Id;


    }
}
