package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.doctordesk.databinding.ActivityPatientEditProfileBinding;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Patient_EditProfile extends AppCompatActivity {
    FirebaseFirestore db;
    PreferenceManager preferencesManager;

    private ActivityPatientEditProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager = new PreferenceManager(getApplicationContext());
       db=FirebaseFirestore.getInstance();


           getupdateData();


       binding.PatientUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               updataPatientData();
           }
       });

    }

    private void getupdateData() {
        binding.PatientEditLoginName.setText(preferencesManager.getString(Constants.KEY_PATIENTS_NAME));
        binding.PatientEditPhoneNumber.setText(preferencesManager.getString(Constants.KEY_PATIENT_PHONE_NUMBER));
        binding.PatientEditAge.setText(preferencesManager.getString(Constants.KEY_PATIENT_AGE));
        binding.PatientEditCity.setText(preferencesManager.getString(Constants.KEY_PATIENT_CITY));
        binding.PatientEditWeight.setText(preferencesManager.getString(Constants.KEY_PATIENT_WEIGHT));
    }

    private void updataPatientData() {


        DocumentReference userRef = db.collection(Constants.KEY_COLLECTION_PATIENTS).document(preferencesManager.getString(Constants.KEY_PATIENT_ID));

        Map<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_PATIENTS_NAME, binding.PatientEditLoginName.getText().toString());
        updates.put(Constants.KEY_PATIENT_PHONE_NUMBER,binding.PatientEditPhoneNumber.getText().toString());
        updates.put(Constants.KEY_PATIENT_AGE, binding.PatientEditAge.getText().toString());
        updates.put(Constants.KEY_PATIENT_CITY, binding.PatientEditCity.getText().toString());
        updates.put(Constants.KEY_PATIENT_WEIGHT,binding.PatientEditWeight.getText().toString());

        userRef.update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        FirebaseFirestore database= FirebaseFirestore.getInstance();
                        database.collection(Constants.KEY_COLLECTION_PATIENTS)
                                .get()
                                .addOnCompleteListener(task -> {
                                    preferencesManager.clear();
                                    if(task.isSuccessful() && task.getResult()!=null && task.getResult().getDocuments().size()>0){
                                        DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(Integer.parseInt(preferencesManager.getString(Constants.KEY_PATIENT_ID)));
                                        Toast.makeText(Patient_EditProfile.this, "Data Updated", Toast.LENGTH_SHORT).show();
                                        preferencesManager.clear();
                                        preferencesManager.putString(Constants.KEY_PATIENTS_NAME,binding.PatientEditLoginName.getText().toString());
                                        preferencesManager.putString(Constants.KEY_PATIENT_PHONE_NUMBER,binding.PatientEditPhoneNumber.getText().toString());
                                        preferencesManager.putString(Constants.KEY_PATIENT_CITY,documentSnapshot.getString(Constants.KEY_PATIENT_CITY));
                                        preferencesManager.putString(Constants.KEY_PATIENT_AGE,binding.PatientEditAge.getText().toString());
                                        preferencesManager.putString(Constants.KEY_PATIENT_WEIGHT,binding.PatientEditWeight.getText().toString());
                                        preferencesManager.putString(Constants.KEY_PATIENT_GENDER,documentSnapshot.getString(Constants.KEY_PATIENT_GENDER));
                                        preferencesManager.putString(Constants.KEY_PATIENT_BLOOD_GROUP,documentSnapshot.getString(Constants.KEY_PATIENT_BLOOD_GROUP));
                                        Patient_MyProfile.update=true;
                                        startActivity(new Intent(getApplicationContext(),Patient_MyProfile.class));

                                    }

                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Patient_EditProfile.this, "Data Updation Failed....", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void getPatientData() {
        
        binding.PatientEditLoginName.setText(preferencesManager.getString(Constants.KEY_PATIENTS_NAME));
        binding.PatientEditPhoneNumber.setText(preferencesManager.getString(Constants.KEY_PATIENT_PHONE_NUMBER));
        binding.PatientEditAge.setText(preferencesManager.getString(Constants.KEY_PATIENT_AGE));
        binding.PatientEditCity.setText(preferencesManager.getString(Constants.KEY_PATIENT_CITY));
        binding.PatientEditWeight.setText(preferencesManager.getString(Constants.KEY_PATIENT_WEIGHT));

    }
}