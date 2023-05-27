package com.example.doctordesk.patient.models;

public class myAppointmentDoctorModel {

    String Dr_Name;
    String Clinic_Name;
    String Clinic_Address;
    String Specialization;
    String Doctor_Id;
    String Appointment_Id;
    String Appointment_Status;
    String Patient_Id;
    String Appointment_Name;
    String Appointment_date;

    String Appointment_time;

    public myAppointmentDoctorModel() {
    }

    public myAppointmentDoctorModel(String dr_Name, String clinic_Name, String clinic_Address, String specialization, String doctor_Id, String appointment_Id, String appointment_Status, String patient_Id, String appointment_Name, String appointment_date,String appointment_time) {
        Dr_Name = dr_Name;
        Clinic_Name = clinic_Name;
        Clinic_Address = clinic_Address;
        Specialization = specialization;
        Doctor_Id = doctor_Id;
        Appointment_Id = appointment_Id;
        Appointment_Status = appointment_Status;
        Patient_Id = patient_Id;
        Appointment_Name = appointment_Name;
        Appointment_date = appointment_date;
        Appointment_time = appointment_time;
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

    public String getAppointment_Name() {
        return Appointment_Name;
    }

    public void setAppointment_Name(String appointment_Name) {
        Appointment_Name = appointment_Name;
    }

    public String getAppointment_date() {
        return Appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        Appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return Appointment_time;
    }

    public void setAppointment_tate(String appointment_time) {
        Appointment_time = appointment_time;
    }
}

