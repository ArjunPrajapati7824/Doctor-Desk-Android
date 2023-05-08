package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorMyPatientBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Doctor_MyPatient extends AppCompatActivity {
ActivityDoctorMyPatientBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorMyPatientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.BnViewDoc.setSelectedItemId(R.id.MyPtient);
        binding.BnViewDoc.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.MyProfile:
                        startActivity(new Intent(getApplicationContext(), Doctor_Profile.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.MyPtient:
                        return true;
                    case R.id.Appointment:
                        startActivity(new Intent(getApplicationContext(), Doctor_Appointment.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.message:
                        startActivity(new Intent(getApplicationContext(), Doctor_Message.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}