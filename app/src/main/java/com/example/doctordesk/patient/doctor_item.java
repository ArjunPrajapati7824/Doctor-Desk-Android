package com.example.doctordesk.patient;
//class for the list of the Doctor and the layout is at resource file

public class doctor_item {
    String Name;
    String Specialization;
    String Clinic_name;

    public doctor_item(String name, String specialization, String clinic_name) {
        Name = name;
        Specialization = specialization;
        Clinic_name = clinic_name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getClinic_name() {
        return Clinic_name;
    }

    public void setClinic_name(String clinic_name) {
        Clinic_name = clinic_name;
    }
}
