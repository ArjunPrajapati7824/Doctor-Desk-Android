package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorMessageBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Doctor_Message extends AppCompatActivity {

    ActivityDoctorMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.BnViewDoc.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id= item.getItemId();

                if(id==R.id.MyProfile){
                    Intent i=new Intent(getApplicationContext(),Doctor_Profile.class);
                    startActivity(i);
                    finish();
                }
                if(id==R.id.Appointment){
                    Intent i=new Intent(getApplicationContext(),Doctor_Appointment.class);
                    startActivity(i);
                    finish();
                }
                if(id==R.id.MyPtient){
                    Intent i=new Intent(getApplicationContext(),Doctor_MyPatient.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}