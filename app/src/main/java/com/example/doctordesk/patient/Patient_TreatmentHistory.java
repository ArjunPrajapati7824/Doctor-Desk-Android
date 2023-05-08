package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientTreatmentHistoryBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Patient_TreatmentHistory extends AppCompatActivity {
ActivityPatientTreatmentHistoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientTreatmentHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.BnView.setSelectedItemId(R.id.History);
        binding.BnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();

                if(id==R.id.MyDoctor){
                    startActivity(new Intent(getApplicationContext(), Patient_MyDoctor.class));
                    overridePendingTransition(0 ,0);
                    finish();
                    return true;

                }
                else if(id==R.id.Appointment){
                    startActivity(new Intent(getApplicationContext(), Patient_Appointment.class));
                    overridePendingTransition(0 ,0);
                    finish();
                    return true;
                }
                else if(id==R.id.SearchDoctor){
                    startActivity(new Intent(getApplicationContext(), Patient_DoctorSearch.class));
                    overridePendingTransition(0 ,0);
                    finish();
                    return true;
                }

                else if(id==R.id.MyProfile){
                    startActivity(new Intent(getApplicationContext(), Patient_MyProfile.class));
                    overridePendingTransition(0 ,0);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }
}