package com.example.doctordesk.patient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityAppointmentBookingBinding;
import com.example.doctordesk.doctor.Doctor_Profile;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AppointmentBooking extends AppCompatActivity {

    private ActivityAppointmentBookingBinding binding;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    PreferenceManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAppointmentBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencesManager = new PreferenceManager(getApplicationContext());


        radioGroup = (RadioGroup) findViewById(R.id.ApPatientGender);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                radioButton=group.findViewById(i);
            }
        });

        binding.BookAppointment.setOnClickListener(view-> bookAppintment());

    }

    void bookAppintment(){

            Loading(true);
            FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();
            String uid =firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document().getId();

            HashMap<String, Object> user = new HashMap<>();
            //put data in database
            user.put(Constants.KEY_APPOINTMENT_NAME, binding.ApPatientName.getText().toString());
            user.put(Constants.KEY_APPOINTMENT_PHONE_NUMBER, binding.ApPatientPhoneNumber.getText().toString());
            user.put(Constants.KEY_APPOINTMENT_AGE, binding.ApPatientAge.getText().toString());
            user.put(Constants.KEY_APPOINTMENT_GENDER, radioButton.getText().toString());
            user.put(Constants.KEY_APPOINTMENT_DESCRIPTION, binding.ApDescription.getText().toString());
            user.put(Constants.KEY_APPOINTMENT_ID,uid);
            user.put(Constants.KEY_PATIENT_ID,preferencesManager.getString(Constants.KEY_PATIENT_ID));
            user.put(Constants.KEY_DOCTOR_ID,getIntent().getStringExtra(Constants.KEY_DOCTOR_ID));
            user.put(Constants.KEY_APPOINTMENT_STATUS,"False");


            firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS)//create collection name
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener(documentReference -> {
                        Loading(false);
//                   preferencesManager.putString(Constants.KEY_APPOINTMENT_ID,uid.toString());
//                   preferencesManager.putString(Constants.KEY_DOCTOR_ID, documentReference.getId());
//                   preferencesManager.putString(Constants.KEY_DOCTOR_NAME,binding.InputName.getText().toString());
//                    Intent intent = new Intent(getApplicationContext(),PatientHome.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        ShowToast("Waiting For Confirmation");
//                    startActivity(intent);//if signup then go to mainactivity
//                    finish();

                    })
                    .addOnFailureListener(exception -> {
                        Loading(false);
                        ShowToast(exception.getMessage());
                    });

    }

    private void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
        private void Loading(boolean IsLoading) {
            if (IsLoading) {
                binding.BookAppointment.setVisibility(View.INVISIBLE);
                binding.ProgressBar.setVisibility(View.VISIBLE);
            } else {
                binding.ProgressBar.setVisibility(View.INVISIBLE);
                binding.BookAppointment.setVisibility(View.VISIBLE);
            }
        }

    }