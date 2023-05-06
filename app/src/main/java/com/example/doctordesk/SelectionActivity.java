package com.example.doctordesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doctordesk.doctor.DoctorLogin;
import com.example.doctordesk.doctor.Doctor_Profile;
import com.example.doctordesk.patient.PatientLogin;
import com.example.doctordesk.patient.Patient_MyProfile;

public class SelectionActivity extends AppCompatActivity {
Button Patient,Doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Patient=findViewById(R.id.ImPatient);
        Doctor=findViewById(R.id.ImDoctor);

        Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(SelectionActivity.this, PatientLogin.class);
                Intent i = new Intent(SelectionActivity.this, Patient_MyProfile.class);
                startActivity(i);
//                finish();
            }
        });

        Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(SelectionActivity.this, DoctorLogin.class);
                Intent i = new Intent(SelectionActivity.this, Doctor_Profile.class);
                startActivity(i);
//                finish();
            }
        });
    }
}