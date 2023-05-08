package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientAppointmentBinding;
import com.example.doctordesk.doctor.Doctor_Appointment;
import com.example.doctordesk.doctor.Doctor_Message;
import com.example.doctordesk.doctor.Doctor_MyPatient;
import com.google.android.material.navigation.NavigationBarView;

public class Patient_Appointment extends AppCompatActivity {
    ActivityPatientAppointmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.BnView.setSelectedItemId(R.id.Appointment);


        binding.BnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.MyProfile:
                        startActivity(new Intent(getApplicationContext(), Patient_MyProfile.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.SearchDoctor:
                        startActivity(new Intent(getApplicationContext(), Patient_DoctorSearch.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.History:
                        startActivity(new Intent(getApplicationContext(), Patient_TreatmentHistory.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;

                    case R.id.Appointment:
                        return true;
                    case R.id.MyDoctor:
                        startActivity(new Intent(getApplicationContext(), Patient_MyDoctor.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }
}