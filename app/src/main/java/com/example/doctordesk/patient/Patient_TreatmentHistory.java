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

        binding.BnView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id= item.getItemId();

                if(id==R.id.MyDoctor){
                    Intent i=new Intent(getApplicationContext(), Patient_MyDoctor.class);
                    startActivity(i);
                    finish();
                }
                if(id==R.id.Appointment){
                    Intent i=new Intent(getApplicationContext(), Patient_Appointment.class);
                    startActivity(i);
                    finish();
                }
                if(id==R.id.SearchDoctor){
                    Intent i=new Intent(getApplicationContext(), Patient_DoctorSearch.class);
                    startActivity(i);
                    finish();
                }

                if(id==R.id.MyProfile){
                    Intent i=new Intent(getApplicationContext(), Patient_MyProfile.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}