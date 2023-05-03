package com.example.doctordesk.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientLoginBinding;
import com.example.doctordesk.doctor.DoctorRegistretion;
import com.example.doctordesk.doctor.doctor_home;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class PatientLogin extends AppCompatActivity {

    private ActivityPatientLoginBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager=new PreferenceManager(getApplicationContext());

        setListeners();


    }

    private void setListeners(){


        binding.RegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientLogin.this, PatientRegister.class);
                startActivity(i);
            }
        });

        binding.PatientLogin.setOnClickListener(view -> {
            if(isValidSignInDetails())
                SignIn();

        });


    }

    private void SignIn(){
        Loading(true);
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_PATIENTS)
                .whereEqualTo(Constants.KEY_PATIENT_PHONE_NUMBER,binding.PatientLoginNumber.getText().toString())
                .whereEqualTo(Constants.KEY_PATIENT_PASSWORD,binding.PatientLoginPass.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult()!=null && task.getResult().getDocuments().size()>0){
                        DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
//                        preferenceManager.putBoolean(Constants.KEY_IS_DOCTOR_SIGNED_IN,true);
//                        preferenceManager.putString(Constants.KEY_DOCTOR_ID,documentSnapshot.getId());
//                        preferenceManager.putString(Constants.KEY_DOCTOR_NAME,documentSnapshot.getString(Constants.KEY_DOCTOR_NAME));
                        Intent i_login=new Intent(getApplicationContext(), PatientHome.class);
                        i_login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i_login);
                    }else {
                        Loading(false);
                        ShowToast("Invalid Mobile number Orv  password");
                    }

                });
    }
    private void Loading(boolean isLoading) {
        if (isLoading) {
            binding.PatientLogin.setVisibility(View.INVISIBLE);
            binding.ProgressBar.setVisibility(View.VISIBLE);
        } else {
            binding.PatientLogin.setVisibility(View.VISIBLE);
            binding.ProgressBar.setVisibility(View.INVISIBLE);

        }

    }


    private void ShowToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private Boolean isValidSignInDetails(){
        if(binding.PatientLoginNumber.getText().toString().trim().isEmpty()){
            ShowToast("Enter Mobile Number");
            return false;
        }else if (binding.PatientLoginNumber.getText().toString().length() != 10) {
            ShowToast("Enter Valid Mobile Number");
            return false;
        } else if (binding.PatientLoginPass.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Password");
            return false;
        }else {
            return true;
        }

    }
}