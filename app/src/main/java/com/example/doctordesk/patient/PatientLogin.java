package com.example.doctordesk.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doctordesk.R;

public class PatientLogin extends AppCompatActivity {
    TextView registerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        registerText=findViewById(R.id.RegisterText);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientLogin.this, PatientRegister.class);
                startActivity(i);
            }
        });
    }
}