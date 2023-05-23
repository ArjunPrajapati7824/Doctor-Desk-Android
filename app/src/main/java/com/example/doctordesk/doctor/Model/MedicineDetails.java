package com.example.doctordesk.doctor.Model;

public class MedicineDetails {
    private String medicineName;
    private String quantity;
    private String frequency;
    private String route;
    private String days;
    private String instruction;

    public MedicineDetails(String medicineName, String quantity, String frequency, String route, String days, String instruction) {
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.frequency = frequency;
        this.route = route;
        this.days = days;
        this.instruction = instruction;
    }


    public String getMedicineName() {
        return medicineName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getRoute() {
        return route;
    }

    public String getDays() {
        return days;
    }

    public String getInstruction() {
        return instruction;
    }
}
