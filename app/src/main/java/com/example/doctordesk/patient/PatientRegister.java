package com.example.doctordesk.patient;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doctordesk.R;

public class PatientRegister extends AppCompatActivity {
    Button PatientSingin;
    TextView  LoginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        LoginText=findViewById(R.id.LoginText);
        PatientSingin=findViewById(R.id.PatientSingin);

        LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientRegister.this, PatientLogin.class);
                startActivity(i);
            }
        });

        PatientSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientRegister.this, PatientHome.class);
                startActivity(i);
            }
        });
    }
}