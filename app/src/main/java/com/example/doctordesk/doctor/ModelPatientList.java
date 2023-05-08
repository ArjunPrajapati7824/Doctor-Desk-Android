package com.example.doctordesk.doctor;

public class ModelPatientList {
    String name,gender,age,number;

    public ModelPatientList(String name, String gender, String age, String number) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.number = number;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
