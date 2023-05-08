package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientDoctorSearchBinding;
import com.example.doctordesk.databinding.ActivityPatientMyDoctorBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Patient_MyDoctor extends AppCompatActivity {
ActivityPatientMyDoctorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPatientMyDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.BnView.setSelectedItemId(R.id.MyDoctor);
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
                        startActivity(new Intent(getApplicationContext(), Patient_Appointment.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.MyDoctor:
                        return true;
                }
                return false;
            }
        });
    }
}