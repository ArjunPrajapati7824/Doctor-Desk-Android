package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientAppointmentBinding;
import com.example.doctordesk.databinding.ActivityPatientDoctorSearchBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Patient_DoctorSearch extends AppCompatActivity {
    ActivityPatientDoctorSearchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientDoctorSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.BnView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id= item.getItemId();

                if(id==R.id.MyProfile){
                    Intent i=new Intent(getApplicationContext(), Patient_MyProfile.class);
                    startActivity(i);
                    finish();
                }
                if(id==R.id.Appointment){
                    Intent i=new Intent(getApplicationContext(), Patient_Appointment.class);
                    startActivity(i);
                    finish();
                }
                if(id==R.id.MyDoctor){
                    Intent i=new Intent(getApplicationContext(), Patient_MyDoctor.class);
                    startActivity(i);
                    finish();
                }

                if(id==R.id.History){
                    Intent i=new Intent(getApplicationContext(), Patient_TreatmentHistory.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}