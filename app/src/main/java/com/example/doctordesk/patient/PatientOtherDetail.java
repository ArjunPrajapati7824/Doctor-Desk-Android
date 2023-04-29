package com.example.doctordesk.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctordesk.R;

public class PatientOtherDetail extends AppCompatActivity {
Button patientContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_other_detail);
        patientContinue=findViewById(R.id.Continue);
        patientContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientOtherDetail.this, PatientHome.class);
                startActivity(i);
            }
        });
    }
}