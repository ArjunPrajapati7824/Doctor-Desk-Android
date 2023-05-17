package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.SelectionActivity;
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
    ActivityPatientMyProfileBinding binding;

    ActivityPatientEditProfileBinding binding2;
    PreferenceManager preferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientMyProfileBinding.inflate(getLayoutInflater());
        binding2=ActivityPatientEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(this, "Welcome again", Toast.LENGTH_SHORT).show();
        binding.BnView.setSelectedItemId(R.id.MyProfile);
        preferencesManager = new PreferenceManager(getApplicationContext());
        LoadPatientDetails();


        binding.PatientLogout.setOnClickListener(view -> SignOut());

        binding.PatientEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(Patient_MyProfile.this,Patient_EditProfile.class));
            }
        });
        binding.PatientLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore database= FirebaseFirestore.getInstance();
                DocumentReference documentReference=
                        database.collection(Constants.KEY_COLLECTION_PATIENTS).document(preferencesManager.getString(Constants.KEY_PATIENT_ID));
                HashMap<String,Object> updates=new HashMap<>();
                documentReference.update(updates).addOnSuccessListener(unused -> {
                    preferencesManager.clear();
                    startActivity(new Intent(getApplicationContext(), SelectionActivity.class));
                    finish();
                }).addOnFailureListener(e-> Toast.makeText(Patient_MyProfile.this, "Unable to sign out...", Toast.LENGTH_SHORT).show());
            }
        });

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

    private void SignOut(){

        FirebaseFirestore database= FirebaseFirestore.getInstance();
        DocumentReference documentReference=
                database.collection(Constants.KEY_COLLECTION_PATIENTS).document(preferencesManager.getString(Constants.KEY_PATIENT_ID));
        HashMap<String,Object> updates=new HashMap<>();
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferencesManager.clear();
                    startActivity(new Intent(getApplicationContext(), PatientLogin.class));
                    finish();
                })
                .addOnFailureListener(e-> Toast.makeText(this, "Unable to sign out...", Toast.LENGTH_SHORT).show());
    }

    public void LoadPatientDetails(){

        binding.PatientNameProfile.setText(preferencesManager.getString(Constants.KEY_PATIENTS_NAME));
        binding.PatientGenderProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_GENDER));
        binding.PatientNumberProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_PHONE_NUMBER));
        binding.PatientCityProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_CITY));
        binding.BloodGroupProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_BLOOD_GROUP));
        binding.PatientAgeProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_AGE));
        binding.WeightPatientProfile.setText(preferencesManager.getString(Constants.KEY_PATIENT_WEIGHT));
    }
}