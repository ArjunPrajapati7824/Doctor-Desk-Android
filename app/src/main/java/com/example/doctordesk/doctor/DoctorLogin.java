package com.example.doctordesk.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doctordesk.R;
import com.example.doctordesk.patient.PatientLogin;
import com.example.doctordesk.patient.PatientRegister;

public class DoctorLogin extends AppCompatActivity {

    TextView registerText;

    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        registerText=findViewById(R.id.RegisterText);

        loginBtn=findViewById(R.id.DoctorLogin);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorLogin.this, DoctorRegistretion.class);
                startActivity(i);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorLogin.this, doctor_home.class);
                startActivity(i);
                finish();
            }
        });
    }
}