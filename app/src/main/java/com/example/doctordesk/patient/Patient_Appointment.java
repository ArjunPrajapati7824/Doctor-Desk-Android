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


        binding.BnView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item)
            {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.MyProfile:
                        Intent i = new Intent(getApplicationContext(), Patient_MyProfile.class);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.SearchDoctor:
                        Intent i2 = new Intent(getApplicationContext(), Patient_DoctorSearch.class);
                        startActivity(i2);
                        finish();
                        break;

                    case R.id.MyDoctor:
                        Intent i3 = new Intent(getApplicationContext(), Patient_MyDoctor.class);
                        startActivity(i3);
                        finish();
                        break;


                    case R.id.History:
                        Intent i4 = new Intent(getApplicationContext(), Patient_TreatmentHistory.class);
                        startActivity(i4);
                        finish();
                        break;

                }
            }

        });

    }
}