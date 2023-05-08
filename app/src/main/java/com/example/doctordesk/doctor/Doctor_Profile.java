package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorProfileBinding;
import com.example.doctordesk.patient.Patient_DoctorSearch;
import com.example.doctordesk.patient.Patient_MyDoctor;
import com.example.doctordesk.patient.Patient_MyProfile;
import com.example.doctordesk.patient.Patient_TreatmentHistory;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.material.navigation.NavigationBarView;

import org.checkerframework.checker.units.qual.C;

public class Doctor_Profile extends AppCompatActivity {
ActivityDoctorProfileBinding binding;
    PreferenceManager preferencesManager = new PreferenceManager(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        LoadUserDetails();
        binding.BnViewDoc.setSelectedItemId(R.id.MyProfile);
        binding.BnViewDoc.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.MyProfile:
                        return true;
                    case R.id.MyPtient:
                        startActivity(new Intent(getApplicationContext(), Doctor_MyPatient.class));
                        overridePendingTransition(0 ,0);
                        finish();
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

    private void LoadUserDetails(){

        binding.DoctorNameProfile.setText(preferencesManager.getString(Constants.KEY_DOCTOR_NAME));
        binding.DoctorClinicNameProfile.setText(preferencesManager.getString(Constants.KEY_CLINIC_NAME));
        binding.DoctorNumberProfile.setText(preferencesManager.getString(Constants.KEY_DOCTOR_PHONENUMBER));
        binding.DoctorAdressProfile.setText(preferencesManager.getString(Constants.KEY_CLINIC_ADDRESS));
        binding.DoctorREGNOProfile.setText(preferencesManager.getString(Constants.KEY_DOCTOR_REGISTRATION_NUMBER));
        binding.DoctorSpecProfile.setText(preferencesManager.getString(Constants.KEY_SPECIALIZATION));


    }
}