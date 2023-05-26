package com.example.doctordesk.doctor;

public class ModelPatientList {
    String Appointment_Name, Appointment_Gender, Appointment_Age, Appointment_Phone_Number, Appointment_Id, Appointment_Status,Appointment_date,Appointment_time;

    public ModelPatientList() {
    }

    public ModelPatientList(String appointment_Name, String appointment_Gender, String appointment_Age, String appointment_Phone_Number, String appointment_Id, String appointment_Status, String appointment_date, String appointment_time) {
        Appointment_Name = appointment_Name;
        Appointment_Gender = appointment_Gender;
        Appointment_Age = appointment_Age;
        Appointment_Phone_Number = appointment_Phone_Number;
        Appointment_Id = appointment_Id;
        Appointment_Status = appointment_Status;
        Appointment_date = appointment_date;
        Appointment_time = appointment_time;
    }

    public String getAppointment_Name() {
        return Appointment_Name;
    }

    public void setAppointment_Name(String appointment_Name) {
        Appointment_Name = appointment_Name;
    }

    public String getAppointment_Gender() {
        return Appointment_Gender;
    }

    public void setAppointment_Gender(String appointment_Gender) {
        Appointment_Gender = appointment_Gender;
    }

    public String getAppointment_Age() {
        return Appointment_Age;
    }

    public void setAppointment_Age(String appointment_Age) {
        Appointment_Age = appointment_Age;
    }

    public String getAppointment_Phone_Number() {
        return Appointment_Phone_Number;
    }

    public void setAppointment_Phone_Number(String appointment_Phone_Number) {
        Appointment_Phone_Number = appointment_Phone_Number;
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

    public String getAppointment_date() {
        return Appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        Appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return Appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        Appointment_time = appointment_time;
    }
}

