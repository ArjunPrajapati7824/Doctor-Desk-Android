package com.example.doctordesk.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doctordesk.R;

public class DoctorRegistretion extends AppCompatActivity {
    Button DoctorRegister;
    TextView LoginText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registretion);
        LoginText=findViewById(R.id.RegLoginText);
        DoctorRegister=findViewById(R.id.DoctorSignup);
        DoctorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorRegistretion.this,doctor_home.class);
                startActivity(i);
            }
        });

        LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorRegistretion.this,DoctorLogin.class);
                startActivity(i);
            }
        });



    }
}