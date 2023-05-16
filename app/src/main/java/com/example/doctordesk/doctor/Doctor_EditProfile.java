package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorEditProfileBinding;
import com.example.doctordesk.databinding.ActivityPatientEditProfileBinding;
import com.example.doctordesk.patient.Patient_EditProfile;
import com.example.doctordesk.patient.Patient_MyProfile;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Doctor_EditProfile extends AppCompatActivity {

    PreferenceManager preferencesManager;
    private ActivityDoctorEditProfileBinding binding;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager = new PreferenceManager(getApplicationContext());
        db= FirebaseFirestore.getInstance();

        getDoctorData();

        binding.DoctorUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDoctorData();
            }
        });
    }

    private void updateDoctorData(){

        DocumentReference userRef = db.collection(Constants.KEY_COLLECTION_DOCTORS).document(preferencesManager.getString(Constants.KEY_DOCTOR_ID));

        Map<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_DOCTOR_NAME, binding.DoctorEditRegName.getText().toString());
        updates.put(Constants.KEY_DOCTOR_PHONENUMBER,binding.DoctorEditPhoneNumber.getText().toString());
        updates.put(Constants.KEY_CLINIC_NAME, binding.DoctorEditClinicName.getText().toString());
        updates.put(Constants.KEY_CLINIC_ADDRESS, binding.DoctorEditClinicAddress.getText().toString());
        updates.put(Constants.KEY_SPECIALIZATION,binding.DoctorEditSpecialization.getText().toString());

        userRef.update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        FirebaseFirestore database= FirebaseFirestore.getInstance();
                        database.collection(Constants.KEY_COLLECTION_DOCTORS)
                                .get()
                                .addOnCompleteListener(task -> {
                                    preferencesManager.clear();
                                    if(task.isSuccessful() && task.getResult()!=null && task.getResult().getDocuments().size()>0){
                                        DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(Integer.parseInt(preferencesManager.getString(Constants.KEY_DOCTOR_ID)));
                                        Toast.makeText(Doctor_EditProfile.this, "Data Updated", Toast.LENGTH_SHORT).show();
                                        preferencesManager.clear();
                                        preferencesManager.putString(Constants.KEY_DOCTOR_NAME,binding.DoctorEditRegName.getText().toString());
                                        preferencesManager.putString(Constants.KEY_DOCTOR_PHONENUMBER,binding.DoctorEditPhoneNumber.getText().toString());
                                        preferencesManager.putString(Constants.KEY_CLINIC_NAME,binding.DoctorEditClinicName.getText().toString());
                                        preferencesManager.putString(Constants.KEY_CLINIC_ADDRESS,binding.DoctorEditClinicAddress.getText().toString());
                                        preferencesManager.putString(Constants.KEY_SPECIALIZATION,binding.DoctorEditSpecialization.getText().toString());
                                        preferencesManager.putString(Constants.KEY_DOCTOR_REGISTRATION_NUMBER,documentSnapshot.getString(Constants.KEY_DOCTOR_REGISTRATION_NUMBER));

                                        startActivity(new Intent(getApplicationContext(), Doctor_Profile.class));

                                    }

                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Doctor_EditProfile.this, "Data Updation Failed....", Toast.LENGTH_SHORT).show();
                    }
                });


    }
    private void getDoctorData() {

        binding.DoctorEditRegName.setText(preferencesManager.getString(Constants.KEY_DOCTOR_NAME));
        binding.DoctorEditPhoneNumber.setText(preferencesManager.getString(Constants.KEY_DOCTOR_PHONENUMBER));
        binding.DoctorEditClinicName.setText(preferencesManager.getString(Constants.KEY_CLINIC_NAME));
        binding.DoctorEditClinicAddress.setText(preferencesManager.getString(Constants.KEY_CLINIC_ADDRESS));
        binding.DoctorEditSpecialization.setText(preferencesManager.getString(Constants.KEY_SPECIALIZATION));
    }
}