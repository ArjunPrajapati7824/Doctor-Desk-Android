package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorAppointmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Doctor_Appointment extends AppCompatActivity {
ActivityDoctorAppointmentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorAppointmentBinding.inflate(getLayoutInflater());
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
                if(id==R.id.message){
                    Intent i=new Intent(getApplicationContext(),Doctor_Message.class);
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