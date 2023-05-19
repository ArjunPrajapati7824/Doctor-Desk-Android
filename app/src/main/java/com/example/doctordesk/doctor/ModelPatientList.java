package com.example.doctordesk.doctor;

public class ModelPatientList {
    String Appointment_Name,Appointment_Gender,Appointment_Age,Appointment_Phone_Number,Appointment_Id;

    public ModelPatientList() {
    }

    public ModelPatientList(String appointment_Name, String appointment_Gender, String appointment_Age, String appointment_Phone_Number,String appointment_Id) {
        Appointment_Name = appointment_Name;
        Appointment_Gender = appointment_Gender;
        Appointment_Age = appointment_Age;
        Appointment_Phone_Number = appointment_Phone_Number;
        Appointment_Id=appointment_Id;
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

}
