package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientEditProfileBinding;
import com.example.doctordesk.databinding.ActivityPatientMyProfileBinding;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Patient_MyProfile extends AppCompatActivity {
    private ActivityPatientMyProfileBinding binding;

    ActivityPatientEditProfileBinding binding2;
    PreferenceManager preferencesManager;
    public static boolean update = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientMyProfileBinding.inflate(getLayoutInflater());
        binding2=ActivityPatientEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.BnView.setSelectedItemId(R.id.MyProfile);
        preferencesManager = new PreferenceManager(getApplicationContext());

        LoadPatientDetails();


        binding.PatientEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(Patient_MyProfile.this,Patient_EditProfile.class));
            }
        });


        binding.PatientLogout.setOnClickListener(view -> logout());
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

                    case R.id.PAppointment:
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



    private void ShowToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void logout(){
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        DocumentReference documentReference=
                database.collection(Constants.KEY_COLLECTION_PATIENTS).document(preferencesManager.getString(Constants.KEY_PATIENT_ID));
        HashMap<String,Object> updates=new HashMap<>();
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferencesManager.clear();
                    startActivity(new Intent(getApplicationContext(),PatientLogin.class));
                    finish();
                })
                .addOnFailureListener(e-> ShowToast("Unable to Sign out"));
    }

    private void LoadPatientDetails(){

        binding.PatientNameProfile.setText(preferencesManager.getString(Constants.KEY_PATIENTS_NAME));
        binding.PatientGenderProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_GENDER));
        binding.PatientNumberProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_PHONE_NUMBER));
        binding.PatientCityProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_CITY));
        binding.BloodGroupProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_BLOOD_GROUP));
        binding.PatientAgeProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_AGE));
        binding.WeightPatientProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_WEIGHT));
    }
}