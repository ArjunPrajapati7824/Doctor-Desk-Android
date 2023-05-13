package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientMyProfileBinding;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.material.navigation.NavigationBarView;

public class Patient_MyProfile extends AppCompatActivity {
ActivityPatientMyProfileBinding binding;
    PreferenceManager preferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientMyProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.BnView.setSelectedItemId(R.id.MyProfile);
        preferencesManager = new PreferenceManager(getApplicationContext());
        LoadUserDetails();


        binding.BnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.MyProfile:
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
                        startActivity(new Intent(getApplicationContext(), Patient_MyDoctor.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    private void LoadUserDetails(){

        binding.PatientNameProfile.setText(preferencesManager.getString(Constants.KEY_PATIENTS_NAME));
        binding.PatientGenderProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_GENDER));
        binding.PatientNumberProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_PHONE_NUMBER));
        binding.PatientCityProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_CITY));
        binding.BloodGroupProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_BLOOD_GROUP));
        binding.PatientAgeProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_AGE));
        binding.WeightPatientProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_WEIGHT));
    }
}